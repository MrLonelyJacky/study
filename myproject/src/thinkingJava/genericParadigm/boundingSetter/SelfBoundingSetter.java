package thinkingJava.genericParadigm.boundingSetter;

/**
 * Created by 15151 on 2019/5/6.
 */
public interface SelfBoundingSetter<T extends SelfBoundingSetter<T>> {
    void set(T arg);
}
