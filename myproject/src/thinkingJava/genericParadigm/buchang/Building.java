package thinkingJava.genericParadigm.buchang;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 15151 on 2019/4/30.
 */
public class Building {
    public static int num(Set<?> set1, Set<?> set2) {
        int result = 0;
        for (Object o1 : set1) {
            if (set2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Set<Object> set2 = new HashSet<>();
        num(set1, set2);

    }

    public static void change(Object o) {
        if (o instanceof Set) {
            Set<?> s = (Set<?>) o;
        }

    }
}
