package concurrent.chapter17;

class WriteLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                readWriteLock.incrementWaitingWriters();
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    //如果当前有线程在读或者写都将进入阻塞
                    System.out.println(Thread.currentThread()+"被阻塞了！！！");
                    readWriteLock.getMUTEX().wait();
                }
            }finally {
                readWriteLock.decrementWaitingReaders();
            }
            //增加正在写操作的线程
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            //减少操作数量
            readWriteLock.decrementWritingWriters();
            //改变锁偏向为读
            readWriteLock.changePrefer(false);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
