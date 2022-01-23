package effectiveJava.chapter2;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by 15151 on 2020/1/9.
 * 消除过期的对象引用  非类型安全
 * 清空过期引用的另一个好处是，如果它们以后又被错误地解除引用，程序就会立即抛
 出Null Po 工nterException 异常，而不是悄悄地错误运行下去。
 */
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITCAPICITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITCAPICITY];
    }

    public void push(Object e) {
        ensureCapicity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        /**
         * 这里为什么要置为null呢，就是消除过期的引用防止内存泄漏
         * 对于stack来说只要size范围内的引用
         */
        elements[size] = null;
        return result;
    }

    /**
     * 扩容
     *
     */
    private void ensureCapicity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
