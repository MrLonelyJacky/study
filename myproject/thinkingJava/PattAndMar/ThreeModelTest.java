package thinkingJava.PattAndMar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/26.
 */
public class ThreeModelTest {
    public static void main(String[] args) {
        String t = "x123xxxxxx123";
        Pattern pattern = Pattern.compile(".*123");
        Matcher matcher = pattern.matcher(t);
        System.out.println("===========贪婪模式================");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        Pattern pattern1 = Pattern.compile(".*?123");
        Matcher matcher1 = pattern1.matcher(t);
        System.out.println("===========懒惰模式================");
        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }
        Pattern pattern2 = Pattern.compile(".*+123");
        Matcher matcher2 = pattern2.matcher(t);
        System.out.println("===========侵入模式================");
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }
    }

}
