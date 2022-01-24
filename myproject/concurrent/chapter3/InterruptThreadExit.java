package concurrent.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/13.
 * 对有些线程比如心跳机制的，如何正确关闭呢 下面是通过捕获
 * 中断信号关闭线程 和通过检查线程的中断标志
 */
public class InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("i will start working");
                while (!isInterrupted()) {
                    //work
                }
                System.out.println("i will be exiting");
            }
        };
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("开始打断！");
        t.interrupt();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("i will start working");
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                System.out.println("i will be exiting");
            }
        };
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("开始打断！");
        t2.interrupt();
    }
}
