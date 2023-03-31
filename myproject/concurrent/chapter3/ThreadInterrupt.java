package concurrent.chapter3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vinci on 2020/4/9.
 * the block thread can be interrupted by the method
 * 打断一个线程并不代表线程生命周期结束，仅仅是中断当前线程阻塞状态
 * 如果当前线程未阻塞也只是将中断标记置为true  若当前线程阻塞则可以捕捉到
 * 中断信号并擦除中断标记
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("oh i am interrupted");
            }
        });
        thread.start();
        //short block and make sure thread start
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
