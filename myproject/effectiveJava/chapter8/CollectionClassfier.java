package effectiveJava.chapter8;

import java.util.*;

/**
 * Created by 15151 on 2020/3/4.
 * 慎用重载 要不用不同数量的参数，要不就别用重载
 * 对于构造器最好的方法是选择导出静态工厂
 * 不要在相同的参数位置调用带有不同函数接口的方法
 */
public class CollectionClassfier {
    public static String classify(Set<?> set) {
        return "set";
    }

    public static String classify(List<?> list) {
        return "list";
    }

    public static String classify(Collection<?> collection) {
        return "unkown type";
    }

    public static String replaceClassify(Collection<?> collection) {
        return collection instanceof Set ? "set" : (collection instanceof List ? "list" : "unknwon type");
    }

    public String dynamicClassify(List<?> list) {
        return "list";
    }

    public String dynamicClassify(Collection<?> collection) {
        return "unkown type";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {new HashSet<String>(), new ArrayList<String>(), new HashMap<String, String>().values()};
        /**
         * 输出 unkown type
         * 重载是编译时确认的 重写则是运行时确认
         */
        for (Collection<?> c : collections) {
            System.out.println(classify(c));
        }
        CollectionClassfier collectionClassfier = new CollectionClassfier();
        for (Collection<?> c : collections) {
            System.out.println(collectionClassfier.dynamicClassify(c));
        }
    }
}
