package concurrent.art.chapter6;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    private final DelayQueue<Data> queue;

    public Producer(DelayQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++) {

            long currentTime = System.nanoTime();
            long validTime = ThreadLocalRandom.current().nextLong(1000000000L, 7000000000L);

            Data data = new Data(currentTime + validTime);
            queue.put(data);

            System.out.println(Thread.currentThread().getName() + ": put " + data);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
