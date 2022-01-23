package thinkingJava.container.set;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by 15151 on 2019/4/1.
 */
public class TypesForSet {

    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        for (int i = 0; i < 10; i++) {
            try {
                set.add(type.getConstructor(int.class).newInstance(i));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return set;
    }

    static <T> void test(Set<T> set, Class<T> type) {
        fill(set, type);
        fill(set, type);
        fill(set, type);
        System.out.println(set);
    }

    public static void main(String[] args) {
        test(new HashSet<>(), HashType.class);
        test(new LinkedHashSet<>(), HashType.class);
        test(new TreeSet<>(), TreeType.class);
        test(new HashSet<>(),SetType.class);
        test(new HashSet<>(),TreeType.class);
    }

}
