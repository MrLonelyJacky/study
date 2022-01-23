package effectiveJava.chapter5;

/**
 * Created by 15151 on 2020/1/9.
 * 接口定义类型并结合泛型使得代码更简洁
 */
public interface GenericityIn<T> {
    T apply(T args);
}
