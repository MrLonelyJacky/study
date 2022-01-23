package concurrent.chapter17;

/**
 * Created by 15151 on 2020/5/27.
 */
public interface Lock {
    //获取显示锁，没有获得锁的线程将被阻塞
    void lock() throws InterruptedException;

    //释放锁
    void unlock();
}
