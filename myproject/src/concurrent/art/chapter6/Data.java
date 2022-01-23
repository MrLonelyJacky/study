package concurrent.art.chapter6;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Data implements Delayed {
    private static final AtomicLong atomic = new AtomicLong(0);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss-n");

    // 数据的失效时间点
    private final long time;

    // 序号
    private final long seqno;

    /**
     * @param deadline 数据失效时间点
     */
    public Data(long deadline)  {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw e;
        }

        this.time = deadline;
        this.seqno = atomic.getAndIncrement();
    }

    /**
     * 返回剩余有效时间
     *
     * @param unit 时间单位
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * 比较两个Delayed对象的大小, 比较顺序如下:
     * 1. 如果是对象本身, 返回0;
     * 2. 比较失效时间点, 先失效的返回-1,后失效的返回1;
     * 3. 比较元素序号, 序号小的返回-1, 否则返回1.
     * 4. 非Data类型元素, 比较剩余有效时间, 剩余有效时间小的返回-1,大的返回1,相同返回0
     */
    @Override
    public int compareTo(Delayed other) {
        if (other == this)  // compare zero if same object
            return 0;

        if (other instanceof Data) {
            Data x = (Data) other;
            int result = Long.compare(this.time, x.time);
            if (result != 0) {
                return result;
            } else {
                return Long.compare(this.seqno, x.seqno);
            }
        } else {
            return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), other.getDelay(TimeUnit.NANOSECONDS));
        }


    }

    @Override
    public String toString() {
        return "Data{" +
                "time=" + time +
                ", seqno=" + seqno +
                "}, isValid=" + isValid();
    }

    private boolean isValid() {
        return this.getDelay(TimeUnit.NANOSECONDS) > 0;
    }

    public static void main(String[] args) {
        try {
            Data data = new Data(100l);
            System.out.printf(String.valueOf(data.time));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}