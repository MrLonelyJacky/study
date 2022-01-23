package concurrent.chapter21;


import java.util.concurrent.TimeUnit;

/**
 * threadLocal 可以用于线程间的数据隔离，所以不会出现数据不安全
 */
public class ThreadLocalSafeDemo {

    public int a = 0;

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void addCount() {
        threadLocal.set(threadLocal.get() + 1);
    }

    public static void main(String[] args) {
        ThreadLocalSafeDemo demo = new ThreadLocalSafeDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i1 = demo.a++;
                threadLocal.set(demo.a);
                addCount();
                System.out.println("a:" + i1);
                System.out.println("threadLocal" + threadLocal.get());
            }).start();
        }
    }
}
