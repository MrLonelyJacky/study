package effectiveJava.chapter4;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by 15151 on 2020/1/8.
 * 如果声明成员类不需要访问外部实例则要用static修饰
 * static嵌套类是独立于外部类的，性能更好，如果非成员内部类的话则会有内存泄漏的危机
 * 有时候非成员内部类也可以运用于Adapter
 * HashMap 中的Entry就是static 修饰的，虽然去掉static也能运行但是每个entry都会有一个引用指向map导致性能降低
 */
public class MySet<E> extends AbstractSet<E> {

    private class MyIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {

        }
    }
}
