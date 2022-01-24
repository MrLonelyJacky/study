package thinkingJava.PattAndMar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/11.
 */
public class PattTest {
    public static void main(String[] args) {
        Pattern pattern =Pattern.compile("\\w+");
        Matcher m = pattern.matcher("haha abc efg");
        //查询前一个匹配的分组
        //System.out.println(m.group());
        while (m.find()){
            System.out.println(m.start()+"-"+(m.end()-1));
            System.out.println(m.group());
        }
        System.out.println(m.groupCount());
    }
}
