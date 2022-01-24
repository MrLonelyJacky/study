package thinkingJava.PattAndMar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/26.
 */
public class TestRegularExpression {
    public static void main(String[] args) {
        String test="Java";
        Pattern pattern = Pattern.compile("(\\w+)");
        Matcher matcher = pattern.matcher(test);
        System.out.println(matcher.groupCount());
        while (matcher.find()){

            System.out.println(matcher.group());
        }
    }
}
