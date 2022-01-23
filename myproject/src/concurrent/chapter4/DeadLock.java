package concurrent.chapter4;

/**
 * Created by vinci on 2020/4/21.
 * 死锁的几个主要原因：1.交叉锁导致死锁 A持有r1锁等待r2锁 B则相反
 * 2.内存不足，线程a需要三十兆内存，cpu已经为他分配了10mb 线程b也需要30mb内存
 * 已经分配了20m 这时候剩余可分配内存为20m，那么两个线程可能都在等待对方能够释放资源
 * //TODO 理解为啥这里要剩余20mb
 * 3.一问一答式数据交互 比如客户端发送消息给服务器，服务器因为某原因错过了，就一直等待客户端
 * 而客户端一直等待服务器
 * 4.数据库锁 //TODO 了解数据库锁机制
 * 5.文件锁 某线程获得文件锁意外退出
 * 6.死循环引起死锁 系统假死
 */
public class DeadLock {
    //TODO 为什么有时候用static修饰，有时候不用
    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WRITE = new Object();

    public void read() {
        synchronized (MUTEX_READ) {
            System.out.println(Thread.currentThread().getName() + "get READ lock");
            synchronized (MUTEX_WRITE) {
                System.out.println(Thread.currentThread().getName() + "get write lock");
            }
            System.out.println(Thread.currentThread().getName() + "release write lock");
        }
        System.out.println(Thread.currentThread().getName() + "release read lock");
    }

    public void write() {
        synchronized (MUTEX_WRITE) {
            System.out.println(Thread.currentThread().getName() + "get write lock");
            synchronized (MUTEX_READ) {
                System.out.println(Thread.currentThread().getName() + "get READ lock");
            }
            System.out.println(Thread.currentThread().getName() + "release read lock");
        }
        System.out.println(Thread.currentThread().getName() + "release write lock");
    }

    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();
        new Thread(() -> {
            while (true) {
                deadLock.read();
            }
        }, "READ-THREAD").start();
        new Thread(() -> {
            while (true) {
                deadLock.write();
            }
        }, "WRITE-THREAD").start();

    }
}
