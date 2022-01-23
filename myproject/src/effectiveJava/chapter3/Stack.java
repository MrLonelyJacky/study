package effectiveJava.chapter3;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by 15151 on 2020/1/9.
 * 可变对象克隆时需要注意必须保证不要伤害原始对象
 */
public class Stack implements Cloneable {
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
     */
    private void ensureCapicity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    public Stack clone() {
        try {
            Stack result = (Stack) super.clone();
            result.elements = this.elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
