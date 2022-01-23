package concurrent.chapter17.stampedLock;

import java.util.concurrent.locks.StampedLock;

public class StampLockedDemo {
    private double x, y;
    //锁
    private final StampedLock stampedLock = new StampedLock();

    //排他锁-写锁
    void move(double deltax, double deltay) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltax;
            y += deltay;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    //乐观读锁
    double distanceFromOrigin() {
        //尝试读取乐观锁（1）
        long stamp = stampedLock.tryOptimisticRead();
        //将全部变量拷贝到方法体栈内（2）
        double currentx = x, currenty = y;
        //检查（1）处获取到读锁票据后，锁有没有被其他锁线程排他性抢占
        if (!stampedLock.validate(stamp)) {
            //如果被抢占则获取一个共享读锁（悲观锁）
            stamp = stampedLock.readLock();
            try {
                //将全部变量拷贝到方法体栈内
                currentx = x;
                currenty = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentx * currentx + currenty * currenty);
    }

    //使用悲观锁获取读锁，并尝试转换为写锁
    void moveIfAtOrigin(double newX,double newY){

    }
}
