package effectiveJava.chapter8;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by 15151 on 2020/3/5.
 * be cautious of using Optional
 * in essence it is a immutable list that has only one element
 * optional means that it does not want to return null or throw exception
 * so do not use optional to return a null value（降低空指针和抛异常带来的性能上的消耗）
 * the client get the optional can give a default value  like 'orElse' 'orElseThrow' methods
 * but not all return type suit optional like list what should return empty list
 * when we use Optional<T> instead of T  after all Optional consumes performance?
 * the rule is : if we do not return result and the return result that the client must perform special handling
 *
 */
public class OptinalUse {
    public static <E extends Comparable<E>> Optional<E> max(Collection<E> collection) {
        if (collection.isEmpty()) {
            return Optional.empty();
        }
        E result = null;
        for (E e : collection) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);
    }
}
