package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.to understand the way of using ReentrantLock
 * 2.compare the syn with ReentrantLock
 */
public class ReentrantLockTest {
    private static final Lock lock = new ReentrantLock();
    private static final Lock fairLock = new ReentrantLock(true);

    public static void main(String[] args) {
       /* new Thread(ReentrantLockTest::easyTest, "线程A").start();
        new Thread(ReentrantLockTest::easyTest, "线程B").start();*/
        new Thread(ReentrantLockTest::fairTest, "线程c").start();
        new Thread(ReentrantLockTest::fairTest, "线程d").start();
        new Thread(ReentrantLockTest::fairTest, "线程e").start();
        new Thread(ReentrantLockTest::fairTest, "线程f").start();
    }

    public static void easyTest() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void fairTest() {
        for (int i = 0; i < 2; i++) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得了锁");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                fairLock.unlock();
            }
        }
    }

    static class ThreadDemo implements Runnable {
        Lock firstLock;
        Lock secondLock;

        public ThreadDemo(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            try {
                firstLock.lockInterruptibly();
                TimeUnit.MILLISECONDS.sleep(50);
                secondLock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("中断正在尝试释放资源！");
                e.printStackTrace();
            }finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放资源");
            }
        }
    }
}
