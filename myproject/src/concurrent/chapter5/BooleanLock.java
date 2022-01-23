package concurrent.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by vinci on 2020/4/25.
 * 使得既具有同步效果，又具备可中断和lock超时功能，也就是控制阻塞时间
 * 这里有一点要特别注意：之前的同步代码块会导致线程阻塞，并且无法中断，
 * 而现在进入blockedList中的线程实际上是获得monitor的线程，但是从逻辑上定义他们为
 * 阻塞线程，调用wait方法而阻塞的线程，并且是真实释放锁资源的，所以他们可以中断的，
 * 概括来说：这些线程实际上不是因为同步代码块而阻塞，是因为逻辑调用了wait方法阻塞的
 */
public class BooleanLock implements Lock {
    private Thread currentThread;
    /**
     * 锁开关，false表示当前锁 没有被任何线程获得或者已经被释放
     * true表示锁被某线程获取，并且该线程是currentThread
     * 逻辑意义上的锁
     */
    private boolean locked = false;
    /**
     * 用来存储哪些线程在获取当前线程时进入阻塞状态
     */
    private final List<Thread> blockedList = new ArrayList<>();

    /**
     * 这个动作加锁，实际上第一个进来的线程可能释放锁资源了，但是其他线程会因为
     * 下面的代码进入阻塞状态，并不是因为同步代码块而阻塞，所以该动作称为加锁lock
     *
     * @throws InterruptedException
     */
    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                if (!blockedList.contains(Thread.currentThread())) {
                    blockedList.add(Thread.currentThread());
                }
                this.wait();
            }
            blockedList.remove(Thread.currentThread());
            this.locked = true;
            System.out.println(Thread.currentThread().getName() + "get the lock");
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get lock during" + mills);
                    }
                    if (!this.blockedList.contains(Thread.currentThread())) {
                        this.blockedList.add(Thread.currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
            }
        }
    }

    /**
     * 只有当前线程才可以释放锁，因为当前线程是逻辑上获取了锁资源的，实际上他也获取过锁资源
     * 但是同步代码块走完它就释放了锁资源，而其他线程在获取锁资源后wait了又释放了锁资源
     * 所以逻辑上来说只有当前线程是获取了锁的，只有他可以释放锁资源，其他线程可能一瞬间获取过锁
     * 但是最终都wait了释放了。
     */
    @Override
    public void unLock() {
        synchronized (this) {
            if (currentThread == Thread.currentThread()) {
                this.locked = false;
                System.out.println(Thread.currentThread().getName() + "release the lock");
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return this.blockedList;
    }
}
