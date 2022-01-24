package thinkingJava.genericParadigm.cachu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/4/29.
 */
public class ListMaker<T> {
    List<T> create() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        ListMaker<String> stringListMaker = new ListMaker<>();
        List<String> strings = stringListMaker.create();
    }

}
