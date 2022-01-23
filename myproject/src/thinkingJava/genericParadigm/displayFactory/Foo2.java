package thinkingJava.genericParadigm.displayFactory;

/**
 * Created by 15151 on 2019/4/30.
 */
public class Foo2<T> {
    private T x;

    public <F extends FactoryI<T>> Foo2(F f) {
        this.x = f.create();
    }
}
