package concurrent.chapter5;

import java.util.LinkedList;

/**
 * Created by 15151 on 2020/4/24.
 * NotifyAll唤醒之后的线程会重新争夺monitor
 */
public class NotifyAll {
    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    //TODO 为什么不直接使用这个max呢 而要定义一个max
    private static final int DEFAULT_MAX_EVENT = 10;

    public NotifyAll() {
        this(DEFAULT_MAX_EVENT);
    }

    public NotifyAll(int max) {
        this.max = max;
    }

    /**
     * 存放活动
     *
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() >= max) {
                System.out.println("the queue is full");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("the event is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    /**
     * 取活动
     *
     * @return
     */
    public Event take() {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                System.out.println("the queue is empty");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            System.out.println("take an event");
            eventQueue.notifyAll();
            return event;
        }
    }

    public static void main(String[] args) {
        NotifyAll notifyAll = new NotifyAll();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 6; j++) {
                    notifyAll.offer(new Event());
                }
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 6; j++) {
                    notifyAll.take();
                }
            }).start();
        }
    }
}
