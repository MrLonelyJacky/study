package thinkingJava.lameda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 15151 on 2019/8/14.
 */
public class LanmedaTest {
    public static void main(String[] args) {
        say(() -> System.out.println("sb"));
        add((a, b) -> a + b, 1, 2);
        List<String> asList = Arrays.asList("a", "ab", "abc", "abcd");
        Stream<String> stream = asList.stream();
        Arrays.asList(1,3,4,5,6,7,8).stream().reduce(new ArrayList<Integer>(),(List<Integer> l,Integer e)->{
            l.add(e);
            return l;
        },(List<Integer> l1,List<Integer> l2)->{
            l1.addAll(l2);
            return l1;
        });
        stream.forEach(a-> System.out.println(a));
        stream.forEach(a-> System.out.println(a));
        //惰性求值
        List<String> upperList = asList.stream().filter(item -> {
            System.out.println("hahah");
            return !"0".equals(item);
        }).map(String::toUpperCase).collect(Collectors.toList());
        Optional<String> s = asList.stream().min(((o1, o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else {
                return -1;
            }
        }));
        System.out.println(s.orElse(""));
        //使用reduce模式求和
        int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
        System.out.println(upperList.toString());

    }

    public static void say(Define define) {
        define.say();
    }

    public static Integer add(Sum sum, Integer a, Integer b) {
        return sum.add(a, b);
    }

    public Set<String> findLong(List<Track> list) {
        Set<String> set = new HashSet<>();
        //重构后对的代码
        Set<String> strings = list.stream().flatMap(track -> track.getTracks().stream())
                .filter(item -> item.length() > 2).map(String::toUpperCase).collect(Collectors.toSet());
        for (Track track : list) {
            for (String item : track.getTracks()) {
                if (item.length() > 2) {
                    set.add(item);
                }
            }
        }
        return set;
    }

    private static class Track {
        List<String> tracks;

        public List<String> getTracks() {
            return tracks;
        }

        public void setTracks(List<String> tracks) {
            this.tracks = tracks;
        }
    }
}
