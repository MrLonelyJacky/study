package thinkingJava.PattAndMar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/11.
 */
public class GroupTest {
    public static void main(String[] args) {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        while (m.find()){
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
        }
        System.out.println("----------分割----------");

        //使用组和不使用组的区别
        Matcher matcher = Pattern.compile("([frb][aiu][gx])").matcher("fix the rug with bags");
        matcher.find();
        System.out.println(matcher.group(0));
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
