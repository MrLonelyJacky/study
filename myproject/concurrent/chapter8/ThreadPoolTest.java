package concurrent.chapter8;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/29.
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        //初始化线程数2 核心数4 最大数6 任务队列最多1000个任务
        final ThreadPool threadPool = new BasicThreadPool(2, 4, 6, 1000);
        //定义20个任务并提交给线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "is running and done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (; ; ) {
            System.out.println("activeCount:" + threadPool.getActiveCount());
            System.out.println("queueSize:" + threadPool.getQueueSize());
            System.out.println("coreSize:" + threadPool.getCoreSize());
            System.out.println("maxSize:" + threadPool.getMaxSize());
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
