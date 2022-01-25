package java8.chapter7;

import java.util.ArrayList;
import java.util.List;

/**
 * @author segi
 * @date 2021/12/30
 * @description
 */
public class Apple {
    private String color;
    private Integer weight;


    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate applePredicate){
        List<Apple> result = new ArrayList<>();
        apples.forEach(apple -> {
            if (applePredicate.test(apple)){
                result.add(apple);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        Apple apple = new Apple("green",10);
        apples.add(apple);
        Apple apple2 = new Apple("red",9);
        apples.add(apple2);
        Apple apple3 = new Apple("yellow",11);
        apples.add(apple3);
        Apple apple4 = new Apple("blue",7);
        apples.add(apple4);
        Apple apple5 = new Apple("black",16);
        apples.add(apple5);

        List<Apple> lessTenApples = filterApples(apples, a -> a.weight < 10);
        System.out.println(lessTenApples);


    }
}
