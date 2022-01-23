package concurrent.chapter7;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/28.
 * 线程抛异常时jvm会调用Thread的dispatcherUncaughtException
 */
public class CaptureThreadException {
    public static void main(String[] args) {
        //设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + "：occur exception");
            e.printStackTrace();
        });
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //抛出未受检异常
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }
}
