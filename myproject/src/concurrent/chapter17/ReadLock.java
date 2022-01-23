package concurrent.chapter17;

/**
 * 设计成包可见，让使用者专注于对接口的调用
 */
class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            //若此时有线程在进行写操作，或者有些线程在等待并且偏向写锁的标识为true，就会无法获得
            //优先写操作然后再读
            //读锁，线程将被挂起
            while (readWriteLock.getWritingWriters() > 0 || (readWriteLock.isPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                System.out.println(Thread.currentThread()+"被阻塞了！！！");
                readWriteLock.getMUTEX().wait();
            }
            //增加正在读的线程数量 只有主动解锁其他线程才真正意义上的去竞争锁资源
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        //同步
        synchronized (readWriteLock.getMUTEX()) {
            //释放锁并将reader--
            readWriteLock.decrementReadingReaders();
            //将preferWriter置为true给予writer更多的机会
            readWriteLock.changePrefer(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
