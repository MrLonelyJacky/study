package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T> {
    private Object[] items;
    //添加的下标，删除的下标和数组当前数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();
    //构造方法
    public BoundedQueue(int size){
        items = new Object[size];
    }
    //添加元素，如果数组满，则添加线程进入等待，直到有空位
    public void add(T t) throws InterruptedException{
        lock.lock();
        try {
            while (count == items.length)  //改成if会如何
                full.await();
            items[addIndex] = t;
            if(++addIndex == items.length)
                addIndex = 0;
            ++count;
            empty.signal();
        }finally {
            lock.unlock();
        }
    }

    //从头部删除一个元素，如果数组空，则删除线程进入等待状态，直到添加新元素
    public T remove() throws InterruptedException{
        lock.lock();
        try{
            while (count == 0)
                empty.await();
            Object x = items[removeIndex];
            if(++removeIndex == items.length)
                removeIndex = 0;
            --count;
            full.signal();
            return (T)x;
        }finally {
            lock.unlock();
        }
    }
}
