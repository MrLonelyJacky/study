package java8.chapter6;

import java.util.*;
import java.util.stream.Collectors;

public class GroupTest {
    public static void main(String[] args) {
        Dish a = new Dish(Dish.Type.FISH,100);
        Dish b = new Dish(Dish.Type.MEAT,800);
        Dish c = new Dish(Dish.Type.MEAT,1200);
        Dish d = new Dish(Dish.Type.OTHER,1000);
        Dish e = new Dish(Dish.Type.FISH,200);
        List<Dish> dishes = Arrays.asList(a, b, c, d, e);
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> collect = dishes.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(dish -> {
                    if (dish.getCalories() >= 1000) return Dish.CaloricLevel.FAT;
                    else if (dish.getCalories() < 1000 && dish.getCalories() > 500) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.DIET;
                })));

        Map<Dish.Type, Set<Dish.CaloricLevel>> collect2 = dishes.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
            if (dish.getCalories() >= 1000) return Dish.CaloricLevel.FAT;
            else if (dish.getCalories() < 1000 && dish.getCalories() > 500) return Dish.CaloricLevel.NORMAL;
            else return Dish.CaloricLevel.DIET;
        }, Collectors.toSet())));

        Map<Dish.Type, Dish> collect1 = dishes.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));


        Map<Boolean, List<Dish>> collect3 = dishes.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));


    }
}
