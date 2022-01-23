package concurrent.art.chapter5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * countDownLatch使用的是共享锁，当阈值为0的时候，多个阻塞的线程将共享state
 */
public class CountDownShare {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        new Thread(()->{
            try {
                latch.await();
                System.out.println("1号获取到共享锁，可以执行我的任务了,我等会二号");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("执行1的任务了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                latch.await();
                System.out.println("2号获取到共享锁，可以执行我的任务了");
                System.out.println("执行2的任务了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
