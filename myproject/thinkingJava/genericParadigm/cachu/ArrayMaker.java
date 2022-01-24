package thinkingJava.genericParadigm.cachu;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by 15151 on 2019/4/29.
 */
public class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringArrayMaker = new ArrayMaker<>(String.class);
        String[] strings = stringArrayMaker.create(9);
        System.out.println(Arrays.toString(strings));
    }
}
