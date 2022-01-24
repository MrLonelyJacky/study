package concurrent.chapter5;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by vinci on 2020/4/25.
 * 定义Lock接口
 * //TODO 拓展tryLock 能获得锁就获得，获取不了就退出，压根不会阻塞
 */
public interface Lock {
    /**
     * lock方法永远阻塞，除非获取到锁，这和synchronized很像
     * 但是可以中断，同时抛出中断异常
     * @throws InterruptedException 中断异常
     */
    void lock() throws InterruptedException;

    /**
     * 除了可中断外还增加了超时异常
     * @param mills
     * @throws InterruptedException 中断异常
     * @throws TimeoutException 超时异常
     */
    void lock(long mills) throws InterruptedException,TimeoutException;

    /**
     * 只有加了自定义锁的线才能解锁
     */
    void unLock();
    List<Thread> getBlockedThreads();
}
