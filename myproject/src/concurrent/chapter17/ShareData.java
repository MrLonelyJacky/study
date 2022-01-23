package concurrent.chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShareData {
    //定义共享数据
    private final List<Character> container = new ArrayList<>();
    //构造readWriteLock
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();
    //创建读取锁
    private final Lock readLock = readWriteLock.readLock();
    //创建写入锁
    private final Lock writeLock = readWriteLock.writeLock();

    private final int size;

    public ShareData(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            container.add(i, 'c');
        }
    }

    public char[] read() throws InterruptedException {
        try {
            readLock.lock();
            char[] charBuffer = new char[size];
            for (int i = 0; i < size; i++) {
                charBuffer[i] = container.get(i);
            }
            slowly();
            return charBuffer;
        } finally {
            //解锁
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            writeLock.lock();
            for (int i = 0; i < size; i++) {
                container.add(i, c);
            }
            slowly();
        } finally {
            //解锁
            writeLock.unlock();
        }
    }

    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
