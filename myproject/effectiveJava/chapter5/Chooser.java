package effectiveJava.chapter5;

import java.util.*;

/**
 * 不能有泛型数组创建但是可以声明 所以通常解决方法是使用集合，虽然会损失一些性能
 * 但是换来的是安全性和互用性   列表优于数组第28条
 *
 * @param <T>
 * @author vinci
 */
public class Chooser<T> {
    private final T[] choiceArray;

    //通过列表的方式 安全和互用性
    private List<T> tList;

    /**
     * 编译器告诉我们他无法在运行的时候知道类型
     * 因为擦除 但是实际上是可以运行的
     *
     * @param choices
     */
    public Chooser(Collection<T> choices) {
        tList = new ArrayList<>(choices);
        //可以加注解 或者采取上面的方式
        choiceArray = (T[]) choices.toArray();
    }


    private Map<Class<?>, Object> favoritesMap = new HashMap<>();

    public <T> void putFavorite(Class<T> typeClass, T instance) {
        favoritesMap.put(Objects.requireNonNull(typeClass), instance);
    }

    public <T> T getFavorite(Class<T> tClass) {
        return tClass.cast(favoritesMap.get(tClass));
    }

    public static void main(String[] args) {

    }
}
