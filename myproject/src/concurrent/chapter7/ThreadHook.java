package concurrent.chapter7;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/28.
 * jvm进程的退出是因为jvm进程中没有活跃的非守护线程，或收到了系统的中断信号
 * 在jvm进程将退出的时候，Hook线程会启动执行
 */
public class ThreadHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("the hook thread 1 is running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the hook thread 1 will exit");
        }));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("the hook thread 2 is running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the hook thread 2 will exit");
        }));
        System.out.println("the program will stop");
    }
}
