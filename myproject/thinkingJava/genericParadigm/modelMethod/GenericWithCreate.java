package thinkingJava.genericParadigm.modelMethod;

/**
 * Created by 15151 on 2019/4/30.
 */
public abstract class GenericWithCreate<T> {
    final T element;

    public GenericWithCreate() {
        this.element = create();
    }

    abstract T create();
}
