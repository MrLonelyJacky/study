package thinkingJava.lameda;

import java.util.function.Function;
import java.util.stream.LongStream;

public class TestParell {
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
    private static class Accumulator {
        public long total = 0;
        public void add(long value) { total += value; }
    }

    public static void main(String[] args) {
        long l = sideEffectSum(10000);
        System.out.println(l);
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
