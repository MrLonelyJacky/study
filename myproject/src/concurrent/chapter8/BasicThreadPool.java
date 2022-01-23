package concurrent.chapter8;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 15151 on 2020/4/28.
 * 他也是Thread的子类，在初始化的时候启动，在keepalive时间间隔到了之后再维护活动线程数量
 * 采用继承方式并不好，会暴露父类Thread中的更多细节，导致语义混论甚至安全漏洞 而且继承打破了封装性导致子类脆弱
 * 试想一下Thread如果在不同版本中做了不同的处理，你继承的子类是否也要做相应的变更呢
 * //TODO 改成组合Thread的方式而非继承
 */
public class BasicThreadPool extends Thread implements ThreadPool {
    //初始化线程数量
    private final int initSize;
    //线程池最大线程数量
    private final int maxSize;
    //线程池核心线程数量
    private final int coreSize;
    //当前活跃线程数量
    private int activeCount;
    //创建线程池所需要的工厂
    private final ThreadFactory threadFactory;
    //任务队列
    private final RunnableQueue runnableQueue;
    //线程池是否已经被shutDown
    private volatile boolean isShutDown = false;
    //工作线程队列 存放active线程
    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();
    //默认拒绝策略
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    //默认线程工厂
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();
    //线程自动维护时间间隔
    private final long keepAliveTime;
    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int coreSize, int maxSize, int queueSize) {
        this(initSize, coreSize, maxSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int coreSize, int maxSize, ThreadFactory threadFactory, int queueSize, DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    //TODO 改成组合方式
    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThead();
        }
    }

    /**
     * 创建线程并绑定internalTask然后加入threadTaskQueue中
     */
    private void newThead() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadTaskQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    /**
     * 将线程移除 并停止该线程当前的活动
     * 需要注意的是回收的线程刚巧拿到了任务，则知道任务完成，才会算真正停止
     */
    private void removeThread() {
        ThreadTask remove = threadTaskQueue.remove();
        remove.internalTask.stop();
        this.activeCount--;
    }

    /**
     * internalTask和thread的组合
     * TODO 为什么绑定起来呢？？？ 不绑定用Thread构造器绑定不也可以吗？
     */
    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    /**
     * 默认工厂
     */
    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndIncrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndIncrement());
        }
    }

    //提交任务
    @Override
    public void execute(Runnable runnable) {
        if (this.isShutDown) {
            throw new IllegalStateException("the pool is shutdown");
        }
        this.runnableQueue.offer(runnable);
    }

    //
    @Override
    public void shutDown() {
        synchronized (this) {
            if (isShutDown) {
                return;
            }
            isShutDown = true;
            threadTaskQueue.forEach(item -> {
                item.internalTask.stop();
                //打断是为了结束 TODO 不打断行不行呢？毕竟运行标记已经为false了 理论上来说已经能结束线程了
                item.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (isShutDown) {
            throw new IllegalStateException("the pool is destroyed!");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutDown) {
            throw new IllegalStateException("the pool is destroyed!");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutDown) {
            throw new IllegalStateException("the pool is destroyed!");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutDown) {
            throw new IllegalStateException("the pool is destroyed!");
        }
        return this.threadTaskQueue.size();
    }

    @Override
    public int getActiveCount() {
        //数据不一致 主线程可能获取的时候 当前线程扩容了
        synchronized (this) {
            if (isShutDown) {
                throw new IllegalStateException("the pool is destroyed!");
            }
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutDown() {
        return isShutDown;
    }

    //用于维护线程 比如扩容、回收等工作 TODO 改成组合的方式
    @Override
    public void run() {
        while (!isShutDown && !isInterrupted()) {
            try {
                //使用类似心跳检测的机制
                timeUnit.sleep(keepAliveTime);
                System.out.println("-----心跳检测等待中-----");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这里用同步代码块 TODO 解释下主线程可能采用shutDown 而线程池自生是个线程他在不断运行 有数据不一致问题
            //比如主线程shutDown了，但是线程池已经走过shutDown标记了 TODO volatile shutDown是否能保证安全呢？？？
            synchronized (this) {
                if (isShutDown) {
                    break;
                }
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    //任务队列 有任务未处理 且active线程数小于核心数则扩容
                    for (int i = initSize; i < coreSize; i++) {
                        newThead();
                    }
                    //这里continue表示当前扩容完成结束了，否则就走下面的代码继续扩容了
                    //而我们想要的是分两次的扩容
                    continue;
                }
                //小于最大数目再扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    for (int i = coreSize; i < maxSize; i++) {
                        newThead();
                    }
                }
                //回收线程，若任务队列中没有任务，回收至核心线程数大小即可
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }
}
