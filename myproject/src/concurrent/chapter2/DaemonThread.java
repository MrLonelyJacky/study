package concurrent.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/3.
 * the main thread ends the daemon thread ends\
 * the gc thread is daemon thread main thread ends gc ends
 * otherwise the jvm can not exist except gc ends
 */
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("main thread has finished");
    }
}
