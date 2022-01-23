package thinkingJava.genericParadigm.crg;

/**
 * Created by 15151 on 2019/5/5.
 */
public class BasicHolder<T> {
    T element;

    void set(T arg) {
        element = arg;
    }

    T get() {
        return element;
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}
