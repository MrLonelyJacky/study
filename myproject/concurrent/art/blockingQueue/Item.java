package concurrent.art.blockingQueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class Item implements Delayed {

    /* 触发时间*/
    private long time;
    String name;

    public Item(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Item item = (Item) o;
        return Long.compare(this.time, item.time);
    }


    @Override
    public String toString() {
        return "Item{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        String poll = blockingQueue.poll(3, TimeUnit.SECONDS);
        if (poll==null){
            System.out.println("等待3s没结果");
        }
        Item item1 = new Item("item1", 5, TimeUnit.SECONDS);
        Item item2 = new Item("item2",10, TimeUnit.SECONDS);
        Item item3 = new Item("item3",15, TimeUnit.SECONDS);
        DelayQueue<Item> queue = new DelayQueue<>();
       /* queue.put(item1);
        queue.put(item2);
        queue.put(item3);*/
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        for (int i = 0; i < 3; i++) {
            Item take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n",take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }
}
