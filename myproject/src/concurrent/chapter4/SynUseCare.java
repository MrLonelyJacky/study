package concurrent.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * Created by vinci on 2020/4/19.
 * 使用时注意：1.MUTEX对象不能为null 2.synchronized作用域不能太大
 * 3.不同的monitor企图锁同一个方法 不同线程关联的锁对象要同一个才能互斥
 * 4.多个锁的交叉导致死锁
 */
public class SynUseCare {
    private final static Object MUTEX = new Object();

    public void accessResource() {
        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final SynUseCare mutex = new SynUseCare();
        for (int i = 0; i < 5; i++) {
            new Thread(mutex::accessResource).start();
        }
    }
}
