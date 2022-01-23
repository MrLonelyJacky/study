package concurrent.chapter5;

import java.util.concurrent.TimeUnit;

/**
 * Created by vinci on 2020/4/25.
 * synchronize 有一些缺陷：
 * 1、无法控制阻塞时间，当调用同步方法时会阻塞，多长时间无法控制
 * 2、阻塞期间无法被打断 虽然中断标志被置为true但是状态仍然是blocked
 */
public class SynchronizedDefect {

    public synchronized void synMethod(){
        try {
            System.out.println(Thread.currentThread().getName()+"正在调用！");
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefect defect = new SynchronizedDefect();
        //t1获取锁后，t2启动线程执行synMethod方法时会进入阻塞状态而且一等就是一小时
        Thread thread = new Thread(defect::synMethod,"t1");
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        Thread thread2 = new Thread(defect::synMethod,"t2");
        thread2.start();
        TimeUnit.SECONDS.sleep(5);
        thread2.interrupt();
        //虽然线程中断标记标为true但是该线程依然阻塞状态等待锁资源
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.getState());
    }
}
