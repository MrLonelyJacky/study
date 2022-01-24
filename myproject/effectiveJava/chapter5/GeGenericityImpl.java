package effectiveJava.chapter5;

import java.util.function.UnaryOperator;

/**
 * Created by 15151 on 2020/1/9.
 * 有时候需要创建一个不可变但又适用于许多不同类型的对象，泛型单例工厂
 * 下面是恒等函数
 */
public class GeGenericityImpl {
    //泛型不能用于静态域
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    private static Object a = new Object();

    /**
     * 声明恒等函数很特殊 他只返回类型同等的对象
     * 因此不会有类型转换异常
     *
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static <T> T get(T t) {
        return (T) a;
    }


    public static void main(String[] args) {
        String[] strings = {"a", "b"};
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings) {
            System.out.println(sameString.apply(s));
        }
        //下列代码自然报错了
        Integer integer = get(3);

    }

}
