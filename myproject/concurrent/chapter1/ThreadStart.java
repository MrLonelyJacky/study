package concurrent.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/3/25.
 * 要不是因为线程重复启动，要不是因为线程生命周期结束被激活而抛出异常
 * 总结：不要重复启动
 */
public class ThreadStart {
    public static void main(String[] args) throws InterruptedException {
        //when new a thread the threadStatus = 0
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //thread start two times what happened?
        thread.start();
        //the main thread blocked the thread0 complete task then can start again????
        TimeUnit.SECONDS.sleep(2);
        //thread cannot repeat starting
        thread.start();
    }
}
