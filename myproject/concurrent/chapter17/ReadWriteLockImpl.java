package concurrent.chapter17;

public class ReadWriteLockImpl implements ReadWriteLock {
    //定义锁对象
    private final Object MUTEX = new Object();

    //定义正在写操作的线程数量 最多为1
    private int writingWriters = 0;
    //定义等待写操作的线程数量
    private int waitingWriters = 0;
    //定义正在读操作的线程数量
    private int readingReaders = 0;
    //read 和 write偏好设置
    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }


    public Object getMUTEX() {
        return MUTEX;
    }

    public boolean isPreferWriter() {
        return preferWriter;
    }

    public void incrementReadingReaders() {
        this.readingReaders++;
    }

    public void decrementReadingReaders() {
        this.readingReaders--;
    }

    public void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public void incrementWritingWriters() {
        this.writingWriters++;
    }

    public void decrementWritingWriters() {
        this.writingWriters--;
    }

    public void incrementWaitingWriters() {
        this.waitingWriters++;
    }


    public void decrementWaitingReaders() {
        this.waitingWriters--;
    }
}
