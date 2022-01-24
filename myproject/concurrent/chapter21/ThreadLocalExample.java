package concurrent.chapter21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ThreadLocal三种使用场景：
 * 1、对象跨层传递 避免方法多次传递，打破层次间的约束
 * 2、线程间数据隔离
 * 3、进行事务操作，用于存储线程事务信息
 */
public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        //创建十个线程
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            //每个线程都会设置threadLocal，彼此间的数据是独立的
            threadLocal.set(i);
            System.out.println(Thread.currentThread() + "set i" + threadLocal.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
    }
}
