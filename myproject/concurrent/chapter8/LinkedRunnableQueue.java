package concurrent.chapter8;

import java.util.LinkedList;

/**
 * Created by 15151 on 2020/4/28.
 * 任务队列，会有多个线程来此提交和取任务，所以要设计成线程安全的方式
 * //TODO 能否替换成自定义显示锁的形式进行同步呢？
 */
public class LinkedRunnableQueue implements RunnableQueue {
    //任务最大容量
    private final int limit;
    //任务已满则执行的拒绝策略
    private final DenyPolicy denyPolicy;
    //存放任务的队列
    private final LinkedList<Runnable> linkedList = new LinkedList<>();
    //线程池
    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    //TODO 目前所学只能做同步提交
    @Override
    public void offer(Runnable runnable) {
        synchronized (linkedList) {
            if (linkedList.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            }
            linkedList.addLast(runnable);
            linkedList.notifyAll();
        }
    }

    //TODO 同步阻塞取任务
    @Override
    public Runnable take() throws InterruptedException {
        synchronized (linkedList) {
            while (linkedList.isEmpty()) {
                linkedList.wait();
            }
            return linkedList.removeFirst();
        }
    }

    //TODO 为了获取大小而同步代码块 是否性能很差
    @Override
    public int size() {
        synchronized (linkedList) {
            return linkedList.size();
        }
    }
}
