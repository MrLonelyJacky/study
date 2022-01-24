package concurrent.art.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 设计一个工具，该工具在同一时刻最多允许两个线程访问，超过
 * 则将阻塞
 */
public class TwinsLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            setState(count);
        }

        @Override
        public int tryAcquireShared(int arg) {
            for (; ; ) {
                int state = getState();
                int currentState = state - arg;
                if (currentState < 0) {
                    return -1;
                }
                if (compareAndSetState(state, currentState)) {
                    return 1;
                }
            }

        }

        @Override
        public boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int state = getState();
                int currentState = state + arg;
                if (currentState > 2) {
                    throw new IllegalStateException("");
                }
                if (compareAndSetState(state, currentState)) {
                    return true;
                }
            }
        }
    }

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }


    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                lock.lock();
                try {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName() + "get the lock");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } finally {
                    System.out.println(Thread.currentThread().getName() + "will release the lock");
                    lock.unlock();
                }
            }

        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }

}
