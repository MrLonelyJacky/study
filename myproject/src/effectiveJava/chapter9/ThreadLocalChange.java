package effectiveJava.chapter9;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/9.
 */
public final class ThreadLocalChange<T> {
    private Map<ThreadLocalChange, T> map = new HashMap<ThreadLocalChange, T>();

    public void set(T value) {
        map.put(this, value);
    }

    public T get() {
        return map.get(this);
    }
}
