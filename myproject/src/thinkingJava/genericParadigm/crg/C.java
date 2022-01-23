package thinkingJava.genericParadigm.crg;

/**
 * Created by 15151 on 2019/5/5.
 */
public class C extends SelfBound<C> {
    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}
