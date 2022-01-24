package concurrent.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/5.
 * 线程sleep期间会释放cpu资源进入阻塞阶段stage
 * but do not consume time slice  turn to be blocked a time
 * yield 会导致线程上下文切换 turn to be runnable
 *
 */
public class YieldAndSleep {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("......");
            try {
                TimeUnit.SECONDS.sleep(15);
                System.out.println("......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //上一个线程虽然运行并且sleep了，但是资源释放又到主线程运行了
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("到我主线程了！");

    }
}
