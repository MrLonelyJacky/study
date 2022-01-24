package thinkingJava.container.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 15151 on 2019/4/2.
 */
public class LinkMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i + "", "a" + i);
        }
        System.out.println(map);

        map = new LinkedHashMap<>(16, 0.75f, true);

    }
}
