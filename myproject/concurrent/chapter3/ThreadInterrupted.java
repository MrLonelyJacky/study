package concurrent.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/10.
 * 很多个false中contains a true
 * the Thread.interrupted is one true else false
 * 如果当前线程被打断了则调用Thread.interrupted第一次返回true后面都是false
 * 这和 thread.isInterrupted不同
 * Thread.interrupted 会擦除中断标志，所以即使线程不在block阶段捕捉到中断标识，也会擦除
 * 所以输出true然后false
 * isInterrupted则不会擦除，只有block阶段被捕捉到中断信号才擦除
 */
public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    System.out.println(isInterrupted());
                }
            }
        };
        //thread.setDaemon(true);
        thread.start();
        //shortly block make sure thread start
        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
    }
}
