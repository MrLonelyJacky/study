package concurrent.chapter5;

/**
 * Created by 15151 on 2020/4/24.
 * 单线程提供者，单线程消费者间的通信
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        //一个线程模拟提供者
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "producer").start();
        //一个线程模拟消费者
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                EventQueue.Event take = eventQueue.take();
            }
        }, "consumer").start();

    }
}
