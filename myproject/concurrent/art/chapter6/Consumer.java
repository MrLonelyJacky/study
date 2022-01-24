package concurrent.art.chapter6;

import java.util.concurrent.DelayQueue;

public class Consumer implements Runnable {
    private final DelayQueue<Data> queue;

    public Consumer(DelayQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++) {
            try {
                Data data = queue.take();
                System.out.println(Thread.currentThread().getName() + ": take " + data);

                Thread.yield();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


        public static void main(String[] args) {
            DelayQueue<Data> queue = new DelayQueue<>();

            Thread c1 = new Thread(new Consumer(queue), "consumer-1");
            Thread p1 = new Thread(new Producer(queue), "producer-1");

            c1.start();
            p1.start();
        }

}