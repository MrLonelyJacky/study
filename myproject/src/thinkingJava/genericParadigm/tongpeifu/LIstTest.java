package thinkingJava.genericParadigm.tongpeifu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 15151 on 2019/6/9.
 * 区分list  list<Object> list<?>
 */
public class LIstTest {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        //
     //   unsafeAdd(strings, new Integer(2));
        //safeAddButLimit(strings, "aa");
        String s = strings.get(0);
        Set<Object> s1 = new HashSet<>();
        s1.add("aaa");
        s1.add(3);
        Set<Integer> s2 = new HashSet<>();
        s2.add(3);
        userRowTypes(s1, s2);
        userRowTypes2(s1, s2);

        if (strings instanceof Set) {
            //换成通配符的形式可以保证安全
            //应该尽量避免原生类型
            Set<?> m = (Set<?>) strings;
            //m.add("a");

        }
    }

    /**
     * 这种原生类型没有安全限定，unchecked编译器提示
     *
     * @param list
     * @param o
     */
    public static void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    /**
     * 这种方式虽然泛型化了，但是参数传递时必须是该类型参数
     * 显然LIst<STRING>不是，string不能等同object
     * 编译器不孕通过
     *
     * @param list
     * @param o
     */
    public static void safeAddButLimit(List<Object> list, Object o) {
        list.add(o);
    }

    public static void safeAddButLimit2(List<?> list, Object o) {
        // list.add(o);
    }

    /**
     * 这种方式原生类型的不要使用
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int userRowTypes(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

    /**
     * 通配符能保证类型安全
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int userRowTypes2(Set<?> s1, Set<?> s2) {

        //s1.add("aa");
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result;
    }
}
