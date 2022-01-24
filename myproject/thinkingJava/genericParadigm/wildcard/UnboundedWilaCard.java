package thinkingJava.genericParadigm.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/5/9.
 */
public class UnboundedWilaCard {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;

    static void assgin2(List<?> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assgin1(List list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assgin3(List<? extends Object> list) {
        list1 = list;
        list2 = list;
        list3 = list;
    }

    public static void main(String[] args) {
        assgin1(new ArrayList());
        assgin2(new ArrayList());
        assgin3(new ArrayList());
        assgin1(new ArrayList<String>());
        assgin2(new ArrayList<String>());
        assgin3(new ArrayList<String>());
        List<?> wildList = new ArrayList<>();
        assgin1(wildList);
        assgin2(wildList);
        assgin3(wildList);
    }
}
