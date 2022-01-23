package effectiveJava.chapter5;

import java.util.List;

/**
 * Created by 15151 on 2020/1/11.
 * 公共的api中通配符优先于参数类型 因为它更简单。将它传到一个列表中（任何列表）
 * 方法就会交换被索引的元素。不用担心类型参数。
 */
public class SwapHelper {
    public static void swap(List<?> list, int i, int j) {
        //list.set(i,list.set(j,list.get(i))); 虽然你从list中取出元素再放入，但是?通配符只能放null
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
