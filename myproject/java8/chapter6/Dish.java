package java8.chapter6;

public class Dish {
    public enum Type {FISH, MEAT, OTHER}

    public enum CaloricLevel {DIET, NORMAL, FAT}

    public Dish(Type type, int calories) {
        this.type = type;
        this.calories = calories;
    }

    private Type type;

    private int calories;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isVegetarian() {
        return this.calories <= 500;
    }
}
