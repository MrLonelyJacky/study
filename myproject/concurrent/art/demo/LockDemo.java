package concurrent.art.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockDemo lockDemo = new LockDemo();
       /* for (int i = 0; i < 5; i++) {
            new Thread(lockDemo::LockTest).start();
        }*/
        /*Thread thread = new Thread(lockDemo::LockInterruptTest);
        thread.start();
        thread.interrupt();*/
        new Thread(lockDemo::LockTest).start();
        boolean tryLock = lockDemo.lock.tryLock(1, TimeUnit.SECONDS);
        if (tryLock){
            try {
                System.out.println("i get lock");
            }finally {
                lockDemo.lock.unlock();
            }
        }else {
            System.out.println("it is overTime!");
        }


    }

    public void LockTest() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + "get lock");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + "release the lock");
            lock.unlock();
        }
    }


    public void LockInterruptTest() {
        try {
            lock.lockInterruptibly();
            System.out.println("get lock");
        } catch (InterruptedException e) {
            System.out.println("lock interrupt");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void tryLockTest() {
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
            System.out.println("get lock int the time");
        } catch (InterruptedException e) {
            System.out.println("lock interrupt");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
