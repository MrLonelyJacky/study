package effectiveJava.chapter7;

import java.util.*;

/**
 * Created by 15151 on 2020/1/15.
 */
public class LambdaBetter {
    private int a;

    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "b");
        /**
         * words要用泛型才能推导
         */
        Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        Collections.sort(words, Comparator.comparingInt(String::length));
        words.sort(Comparator.comparingInt(String::length));
        Map<Integer, Integer> map = new HashMap<>();
        map.merge(3, 1, (count, incr) -> count + incr);
        //count and incr has no value can be replaced by method reference
        //the more parameters the more template code you eliminate
        map.merge(3, 1, Integer::sum);
        LambdaBetter lambdaBetter = new LambdaBetter();
        lambdaBetter.a = 1;
        LambdaBetter diZhi = getDiZhi(lambdaBetter);
        System.out.println(lambdaBetter.a);
    }

    public static LambdaBetter getDiZhi(LambdaBetter lambdaBetter) {
        lambdaBetter.a = 3;
        return lambdaBetter;
    }
}
