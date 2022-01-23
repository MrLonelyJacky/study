package thinkingJava.genericParadigm.Question;

/**
 * Created by 15151 on 2019/5/5.
 */
public class FixedSizeStack<T> {
    private int index = 0;
    private Object[] storage;

    public FixedSizeStack(int size) {
        storage = new Object[size];
    }

    public void push(T item) {
        storage[index++] = item;
    }


    public T pop(){
        //由于擦除无法用泛型类型转型
        return (T) storage[--index];
    }


}
