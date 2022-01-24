package concurrent.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * Created by vinci on 2020/4/21.
 * 虽然还是同一个monitor但是这里的是ClassMonitor
 */
public class ClassMonitor {
    public static synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "enter to method1");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果你把synchronized放在方法里会有同样的效果
     */
    public static synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "enter to method2");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(ClassMonitor::method1).start();
        new Thread(ClassMonitor::method2).start();
    }
}
