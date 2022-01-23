package effectiveJava.chapter5;

import java.util.*;

/**
 * Created by 15151 on 2020/1/9.
 * 类型安全的stack  利用有限制的通配符提高api的灵活性 pecs
 */
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITCAPICITY = 16;


    /**
     * 这种用法是合法的但不是类型安全的，必须自己保证安全，私有化数组，push时只能存放对应类型元素才安全
     * 当然还有一种方法就是Object数组 保证push和pop时类型一致即可
     * 第一种方式优先，因为第二种方式每次pop都要转换一下  但是第一种方式会导致堆污染:数组的运行时类型与它的
     * 编译时类型不匹配（除非E正好是Object）
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        //禁止泛型数组有些讨厌 这表明泛型一般不能返回它的元素类型数组
        this.elements = (E[]) new Object[DEFAULT_INITCAPICITY];
    }

    public void push(E e) {
        ensureCapicity();
        elements[size++] = e;
    }

    public void pushAll(Iterable<? extends E> iterable) {
        for (E e : iterable) {
            push(e);
        }
    }

    /**
     * PECS 灵活 producer extends consumer super
     *
     * @param collection
     */
    public void popAll(Collection<? super E> collection) {
        while (!isEmpty()) {
            collection.add(pop());
        }
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        /**
         * 这里为什么要置为null呢，就是消除过期的引用防止内存泄漏
         * 对于stack来说只要size范围内的引用
         */
        elements[size] = null;
        return result;
    }

    /**
     * 扩容
     */
    private void ensureCapicity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    /**
     * 利用pecs 让同一大类型下的小类型比较
     * Comparable<? super E> 优先于Comparable<E＞
     * @param eList
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> E max(List<? extends E> eList) {
        E result = null;
        for (E e : eList) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("a");
        System.out.println(stringStack.pop().toUpperCase());
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
