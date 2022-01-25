package java8.chapter7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author segi
 * @date 2022/1/6
 * @description
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        List<Integer> collect = list.stream().filter(d -> {
            System.out.println("filter " + d);
            return Integer.parseInt(d) > 1;
        }).map(d -> {
            System.out.println("mapping " + d);
            return Integer.parseInt(d);
        }).limit(3).collect(Collectors.toList());
        System.out.println(collect);
    }

}
