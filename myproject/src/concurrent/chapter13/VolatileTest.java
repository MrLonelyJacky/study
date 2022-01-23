package concurrent.chapter13;

import concurrent.chapter23.CountDownLatch;
import concurrent.chapter23.Latch;

/**
 * volatile能够保证可见性和有序性
 * 对于有序性很直接阻止jvm进行指令重排序
 * 但是为什么保证不了原子性呢？
 */
public class VolatileTest {
    private static volatile int i = 0;
    private static final Latch latch = new CountDownLatch(10);

    private static void inc() {
        //这里编译器已经开始提示了
        i++;
    }

    /**
     * 为什么下面的结果不是10000呢？而且每次结果不同
     * 因为，假设i=100时 线程a进行自增 a首先去内存中找到i的值 这时候b获取到cpu
     * b从内存中获取的也是100 然后执行+1 并刷新到主存中 这时候a执行+1并刷新到主存中
     * 两者都是将主存的值改为101
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int x = 0; x < 1000; x++) {
                    inc();
                }
                latch.countDown();
            }).start();
        }
        //等待所有线程完成工作
        latch.await();
        System.out.println(i);
    }

}
