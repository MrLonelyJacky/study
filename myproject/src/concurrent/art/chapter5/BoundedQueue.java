package concurrent.art.chapter5;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * a bounded queue designed by vinci
 * @param <T>
 */
public class BoundedQueue<T> {
    private Object[] items;
    // 添加的下标，删除的下标和数组当前数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    // 添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            if (count == items.length) {
                //if queue is full then wait
                System.out.println("it will overflow wait...");
                notFull.await();
            }
            items[addIndex++] = t;
            if (addIndex == items.length) {
                addIndex = 0;
            }
            count++;
            System.out.println("add one");
            //make consumer wake up
            notEmpty.signal();//唤醒notEmpty中的线程去消费
        } finally {
            lock.unlock();
        }
    }

    // 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            if (count == 0) {
                //if queue will empty
                System.out.println("it will be empty wait...");
                notEmpty.await();
            }
            Object remove = items[removeIndex];
            //let gc do it
            items[removeIndex] = null;
            removeIndex++;
            if (removeIndex == items.length) {
                removeIndex = 0;
            }
            count--;
            System.out.println("remove one");
            //wake producer up
            notFull.signal();
            return (T) remove;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedQueue<String> boundedQueue = new BoundedQueue<>(5);
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    boundedQueue.add(i + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    boundedQueue.remove();
                   // TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(boundedQueue.count);
    }
}
