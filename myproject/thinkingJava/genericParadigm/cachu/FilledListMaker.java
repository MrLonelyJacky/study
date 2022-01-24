package thinkingJava.genericParadigm.cachu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/4/29.
 */
public class FilledListMaker<T> {
    List<T> create(T t, int n) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(t);
        }
        return result;
    }

    public static void main(String[] args) {
        FilledListMaker<String> filledListMaker = new FilledListMaker<>();
        List<String> list = filledListMaker.create("hello", 4);
        System.out.println(list);
    }
}
