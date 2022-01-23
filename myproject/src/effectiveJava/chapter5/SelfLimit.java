package effectiveJava.chapter5;

import java.util.Collection;
import java.util.Objects;

/**
 * Created by 15151 on 2020/1/9.
 * 泛型自限定  这里可以用更好的方式pecs改写，详见stack类
 */
public class SelfLimit {
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }
}
