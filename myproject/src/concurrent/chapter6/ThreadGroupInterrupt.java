package concurrent.chapter6;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/28.
 * group interrupt all threads in it interrupt
 */
public class ThreadGroupInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("TestGroup");
        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

            }
            System.out.println("t1 will exit");
        },"t1").start();
        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            System.out.println("t2 will exit");
        },"t2").start();
        TimeUnit.SECONDS.sleep(1);
        group.interrupt();
    }
}
