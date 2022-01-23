package thinkingJava.genericParadigm.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 15151 on 2019/5/8.
 */
public class Apply {
    public static <T, S extends Iterable<T>> void apply(S seq, Method f, Object... args) {
        for (T t : seq) {
            try {
                f.invoke(t,args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
