package effectiveJava.chapter4;

import java.util.*;

/**
 * 复合加转发的方式
 * 相比继承更灵活，并且安全性更高
 */
public class InstrumentedSetNotSafe<E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentedSetNotSafe() {
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        addCount += collection.size();
        return super.addAll(collection);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedSetNotSafe<String> notSafe = new InstrumentedSetNotSafe<>();
        notSafe.addAll(Arrays.asList("a","b","c"));
        System.out.println(notSafe.getAddCount());
    }

}
