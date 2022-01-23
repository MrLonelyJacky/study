package concurrent.art.chapter5;

import effectiveJava.chapter6.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {
    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);

    public static void fair() {
        testLock(fairLock);
    }

    public static void unfair() {
        testLock(unfairLock);
    }

    private static void testLock(ReentrantLock2 lock) {
        // 启动5个Job（略）
        for (int i = 0; i < 4; i++) {
            new Job(lock).start();
        }
    }

    private static class Job extends Thread {
        private ReentrantLock2 lock;

        public Job(ReentrantLock2 lock) {
            this.lock = lock;
        }

        public void run() {
            for (int i=0;i<3;i++){
                //TODO 每个线程尝试多次获取锁，发现非公平锁，有的线程会多次连续获取锁，不给别人机会
                lock.lock();
                try {
                    // 连续2次打印当前的Thread和等待队列中的Thread（略）
                    System.out.println(Thread.currentThread().getName() + "get lock");
                    Collection<Thread> queuedThreads = lock.getQueuedThreads();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("waiting by");
                    for (Thread thread : queuedThreads) {
                        stringBuilder.append(thread.getName()).append("、");
                    }
                    System.out.println(stringBuilder.toString());
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }


        }
    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.
                    getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        fair();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        unfair();
    }
}
