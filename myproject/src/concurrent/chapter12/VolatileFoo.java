package concurrent.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/5/6.
 */
public class VolatileFoo {
    final static int MAX = 5;
    static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (init_value != localValue) {
                    System.out.printf("the init value is updated [%d]\n", init_value);
                    localValue = init_value;
                }
            }
        }, "reader").start();
        //启动线程修改init value值，当local_value大于5的时候退出生命周期
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                //修改initValue
                System.out.printf("the init value will be changed to [%d]\n", ++localValue);
                init_value = localValue;
                //短暂休眠为了reader线程能够读取到变化
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "updater").start();
    }
}
