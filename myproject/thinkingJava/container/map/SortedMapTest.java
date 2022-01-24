package thinkingJava.container.map;

import java.util.TreeMap;

/**
 * Created by 15151 on 2019/4/2.
 */
public class SortedMapTest {

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            treeMap.put(i, "a" + i);
        }
        System.out.println(treeMap);
    }

}
