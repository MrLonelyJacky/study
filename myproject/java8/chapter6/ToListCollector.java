package java8.chapter6;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }

    public boolean isPrime(int candidate) {
        int sqrt = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, sqrt).boxed().noneMatch(x -> candidate % x == 0);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(this::isPrime));
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i=0;
        for (A item:list){
            if (!p.test(item)){
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }

    public static void main(String[] args) {


    }
}
