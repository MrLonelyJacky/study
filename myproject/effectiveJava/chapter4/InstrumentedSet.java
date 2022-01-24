package effectiveJava.chapter4;

import java.util.Collection;
import java.util.Set;

/**
 *  复合加转发的方式
 * 相比继承更灵活，并且安全性更高
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount;

    public InstrumentedSet(Set<E> set) {
        super(set);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        addCount += 3;
        return super.addAll(collection);
    }

    public int getAddCount() {
        return addCount;
    }

}
