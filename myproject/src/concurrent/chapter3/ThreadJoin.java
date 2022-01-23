package concurrent.chapter3;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by 15151 on 2020/4/10.
 * join也是一个重要的方法 join某个线程A 会导致当前进程blocked
 * 直到线程A结束生命周期  join方法也会捕捉中断信号
 *
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(Collectors.toList());
        threadList.forEach(Thread::start);
        //执行join
        for (Thread thread : threadList) {
            thread.join();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"#"+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //构造简单线程，每个都循环输出
    private static Thread create(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, String.valueOf(seq));
    }
}
