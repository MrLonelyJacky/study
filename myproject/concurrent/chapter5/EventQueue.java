package concurrent.chapter5;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 15151 on 2020/4/23.
 * wait方法：一旦调用wait方法就会放弃对monitor的所有权，进而进入waitSet中
 * 并且调用wait方法的线程会变成阻塞状态，直到其他线程唤醒，wait方法必须在wait前拥有monitor才能进行释放
 * 也就是wait必须在同步方法中使用
 * notify:唤醒正在执行该对象wait方法的线程
 * 下面的案例仅适合单线程间通信
 */
public class EventQueue {
    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    //TODO 为什么不直接使用这个max呢 而要定义一个max
    private static final int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    /**
     * 存放活动
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                System.out.println("the queue is full");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("the event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    /**
     * 取活动
     * @return
     */
    public Event take(){
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                System.out.println("the queue is empty");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            System.out.println("take an event");
            eventQueue.notify();
            return event;
        }
    }

}
