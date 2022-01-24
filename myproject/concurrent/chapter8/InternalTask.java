package concurrent.chapter8;

/**
 * Created by 15151 on 2020/4/28.
 * 该类会不断从队列中取任务然后运行
 */
public class InternalTask implements Runnable {
    private final RunnableQueue runnableQueue;
    //TODO 为何定义该值呢
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        //如果当前任务未中断则不断去任务运行
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable take = runnableQueue.take();
                take.run();
                System.out.println("任务执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }

        }
    }

    //停止当前任务，主要会在线程池的shutDown方法中使用
    public void stop() {
        this.running = false;
    }
}
