package concurrent.practise.chapter7;

import java.util.concurrent.*;

/**
 * 外部线程中安排中断
 * 不要这样做
 */
public class OutThreadInterrupt {
    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(8);
    private static final BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1000);
    private static final ExecutorService taskExec = new ThreadPoolExecutor(8,10,1,TimeUnit.SECONDS,blockingQueue);
    /**
     * 这个有很多不足之处
     *
     * @param r
     * @param timeOut
     * @param timeUnit
     */
    public static void timedRun(Runnable r, long timeOut, TimeUnit timeUnit) {
        final Thread thread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("一段时间后执行。。。");
                thread.interrupt();
            }
        }, timeOut, timeUnit);
        r.run();
        cancelExec.shutdown();
    }

    /**
     * thread.join有不足之处 不知道控制权的返回是因为线程退出还是join超时
     *
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timeRun2(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Exception t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Exception throwable) {
                    this.t = throwable;
                }
            }

            void rethrow() {
                if (t != null) {
                    //重新抛出
                }
            }
        }
        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("一段时间后尝试中断");
                taskThread.interrupt();
            }
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

    public static void timedRun3(Runnable r, long timeout, TimeUnit unit) {
        Future<?> submit = taskExec.submit(r);
        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //task中的异常重新抛出  这样调用者就能接受到
            e.printStackTrace();
        }finally {
            //如果任务已经结束，是无害的
            submit.cancel(true);//interrupt if running
        }

    }


    public static void main(String[] args) {
        timedRun(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahha");
            }
        }, 3, TimeUnit.SECONDS);


    }
}
