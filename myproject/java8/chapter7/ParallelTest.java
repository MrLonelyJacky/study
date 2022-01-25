package java8.chapter7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParallelTest {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list1.add(i);
        }
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(list1.size());
        List<Integer> streamList = new ArrayList<>();
        Map<Integer, Integer> collect = list1.parallelStream().map(aa -> new HashMap.SimpleEntry<>(aa, aa.hashCode())).collect(Collectors.toMap(var0 -> var0.getKey(), var1 -> var1.getValue()));
        System.out.println(collect.size());
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
