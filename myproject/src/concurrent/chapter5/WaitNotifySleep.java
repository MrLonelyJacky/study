package concurrent.chapter5;

import java.util.Objects;

/**
 * Created by 15151 on 2020/4/24.
 * wait notify 使用注意点，以及wait和sleep区别
 * wait 和 notify:
 * 1、wait也是可中断方法，其他线程可以通过interrupt打断其阻塞状态，可中断方法被打断后会收到
 * 中断异常，并擦除中断标志，这和sleep很像
 * 2、线程执行了某个对象的wait方法，会加入与之对应的wait set中，notify则可以唤醒wait set中的线程
 * 3、必须在同步代码块中使用wait和notify，因为必须获得monitor锁才能使用wait和notify
 * 4、还有一点很重要同步代码块的monitor 必须和调用wait和notify对象一致，简单来说用哪个对象的monitor
 * 就必须调用哪个对象的wait和notify
 * 对于第四点下面代码运行哪个都会报错
 *
 * wait和sleep：
 * 1、都是可中断方法，调用进入阻塞状态、并且可被中断，收到异常并擦除中断标记
 * 2、wait释放锁，sleep不释放
 * 3、wait是Object方法，sleep是Thread方法
 * 4、wait必须在同步代码块中，获取自己对象对应的monitor 但是sleep不用
 * 5、sleep方法必须指定时间
 */
public class WaitNotifySleep {
    private final Object MUTEX = new Object();

    /**
     * 锁是this对象的monitor 而wait是MUTEX的wait
     */
    public synchronized void testWait() {
        try {
            MUTEX.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void testNotify() {
        MUTEX.notify();
    }
}
