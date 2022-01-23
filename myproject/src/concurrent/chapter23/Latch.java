package concurrent.chapter23;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class Latch {
    //用于控制多少个线程完成任务时才能打开阀门
    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    //该方法会让当前线程一直等待，知道所有线程完成工作，被阻塞的线程是允许中断的
    public abstract void await() throws InterruptedException;

    public abstract void await(long time) throws InterruptedException, TimeoutException;

    //任务完成之后调用该方法使得计数器减一
    public abstract void countDown();

    //获取当前还有多少未完成任务
    public abstract int getUnArrived();
}
