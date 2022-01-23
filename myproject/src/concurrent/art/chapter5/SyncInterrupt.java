package concurrent.art.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁中断尝试，countDownLatch共享锁中断，reentrantLock独占锁中断
 */
public class SyncInterrupt {
    /**
     * thread1 获取锁成功！
     * 中断thread1....
     * thread1 释放锁
     * thread2 获取锁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //TODO 获取锁成功后再中断也没用了

        Lock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("thread1 获取锁成功！");
                for (int i = 0; i < 10000000; i++) {

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("我被中断了，如果不调用unlock，那么我会释放锁吗？");
            } finally {

                System.out.println("thread1 释放锁");
                lock.unlock();
            }

        });
        thread.start();
        //保证先获取锁再中断
        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
        System.out.println("中断thread1....");
        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("thread2 获取锁");
                //
            } finally {
                //释放锁
                lock.unlock();
            }

        });
        thread2.start();
    }
}
