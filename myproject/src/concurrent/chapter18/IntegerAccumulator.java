package concurrent.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 不可变对象设计
 */
public final class IntegerAccumulator {
    private final int init;

    //构造时传入初始值
    public IntegerAccumulator(int init) {
        this.init = init;
    }

    //构造累加器将原来的对象传入 生成新的对象
    public IntegerAccumulator(IntegerAccumulator accumulator, int init) {
        this.init = accumulator.getInit() + init;
    }

    public IntegerAccumulator add(int i) {
        return new IntegerAccumulator(this, i);
    }

    public int getInit() {
        return init;
    }

    public static class AccRunnable implements Runnable {
        private IntegerAccumulator accumulator = new IntegerAccumulator(0);

        @Override
        public void run() {
            int inc = 0;

            for (int j = 0; j < 100; j++) {
                int oldValue = accumulator.getInit();
                accumulator = accumulator.add(inc);
                int result = accumulator.getInit();
                System.out.println(oldValue + "+" + inc + "=" + result);
                if (inc + oldValue != result) {
                    System.out.println("ERROR");
                }
                inc++;
                //slowly();
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new AccRunnable();
        IntStream.range(0, 4).forEach(i -> new Thread(runnable).start());
    }

    private static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
