package concurrent.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/21.
 * 请思考：调用了两个同一个对象的不同synchronized方法时，与之对应的monitor是什么？
 * 是否是同一个monitor呢
 * 运行程序你会发现method1 block之后 method2并没有得到运行的机会，因为他们在争夺同一个
 * monitor锁资源
 */
public class ThisMonitor {
    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "enter to method1");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将该方法的synchronized去掉就会有不同的效果
     */
    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "enter to method2");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1).start();
        new Thread(thisMonitor::method2).start();
    }
}
