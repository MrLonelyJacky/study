package effectiveJava.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 15151 on 2020/1/14.
 * 慎用泛型和可变数组
 * 堆污染 参数化类型变量指向不是该类型的变量
 * 非具体化类型 像List<String>这种运行时类型信息比编译时少
 */
public class HeapPollution {
    /**
     * 该方法上就有堆污染，但是这很有趣，为什么不能创建泛型数组
     * 却能传可变参数，可变参数本质上也是数组
     * 答案在于，带有泛型可变参数或者参数化类型
     * 的方法在实践中用处很大，因此Java 语言的设计者选择容忍这一矛盾的存在。
     *
     * @param lists
     */
    static void dangerous(List<String>... lists) {
        List<Integer> integers = Arrays.asList(1, 3);
        //可变参数本质上是数组
        Object[] objects = lists;
        objects[0] = integers;
        String s = lists[0].get(0);
        ArrayList list = new ArrayList<Integer>();
        list.add(100);
        //他是如何保证安全的呢
        List<Integer> integers1 = Arrays.asList(1, 2);
        ArrayList<String> strList = list;
        String p = strList.get(0);//运行时出错
        System.out.println(p);
    }

    /**
     * 那么既然可变参数用处多，如何保证安全呢
     * 虽然可以使用@SafeVarargs注解但保证安全还得注意以下几点
     * 1、泛型数组是在调用可变参数的时候创建的，所以要保证在方法中数组里没有增加元素
     * 2、不要让外部访问数组（注意克隆程序） 可变参数数组是Object[]
     * 3、允许另一个方法访问一个泛型可变参数数组是不安全的（pickTwo例子）
     * 除了:将数组传给另一个用＠ SafeVarargs 正确注解过的可变参数方法是安全(如Arrays.asList(1, 2);)
     * 的，将数组传给只计算数组内容部分函数的非可变参数方法也是安全的。
     * 看下面例子你可能认为很安全其实不然
     *
     * @param args
     * @param <T>
     * @return
     */
    static <T> T[] toArray(T... args) {
        return args;
    }

    /**
     * 编译器会根据类型推导出如返回String[]  但是运行时擦除将只会返回Object[]
     * 这时就会报错
     * @param a
     * @param b
     * @param c
     * @param <T>
     * @return
     */
    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, b);
            case 1:
                return toArray(a, c);
            case 2:
                return toArray(b, c);
            default:
                return toArray(a, b, c);
        }
    }

    /**
     *对于每一个带有泛型可变参数或者参数化类型的方法，都要用＠SafeVarargs 进行注解，
     * 下面是正确的示例
     * @param lists
     * @param <T>
     * @return
     */
    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    public static void main(String[] args) {
        //为何下面代码会报错 Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
        String[] strings = pickTwo("Good", "Fast", "Cheap");
        //为何这段代码不报错呢
        toArray("a", "b");
    }
}
