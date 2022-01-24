package concurrent.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/10.
 * 在sleep之前中断呢  如果注释1的代码和注释3的代码互换位置则会有不同的结果
 * 因为注释一的代码会擦除中断标志 中断方法将不会捕捉到中断信号
 */
public class BeforeSleepInterrupt {
    public static void main(String[] args) {
        //1.判断当前线程是否中断
        System.out.println("Main thread is interrupted?" + Thread.interrupted());
        //2.中断当前线程
        Thread.currentThread().interrupt();
        //3.判断当前线程是否中断 不擦除中断标志
        System.out.println("main thread is interrupted" + Thread.currentThread().isInterrupted());
        try {
            //4.执行中断方法
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("interrupt still");
        }
    }
}
