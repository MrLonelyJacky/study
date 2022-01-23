package concurrent.chapter23;

import java.util.concurrent.TimeoutException;

public class CountDownLatch extends Latch {
    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            //limit > 0 当前线程阻塞
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    public void await(long waitTime) throws InterruptedException, TimeoutException {
        synchronized (this) {
            long currentTime = System.currentTimeMillis();
            long targetTime = currentTime + waitTime;
            while (limit > 0) {
                if (waitTime < 0) {
                    throw new TimeoutException("等待超时！！");
                }
                this.wait(waitTime);
                waitTime = targetTime - System.currentTimeMillis();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived");
            }
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnArrived() {
        return limit;
    }
}
