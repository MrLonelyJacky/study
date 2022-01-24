package thinkingJava.genericParadigm.crg;

/**
 * Created by 15151 on 2019/5/5.
 */
public class SelfBound<T extends SelfBound<T>> {
    T element;

    SelfBound<T> set(T arg){
        element =arg;
        return this;
    }

    T get(){
        return element;
    }
}
