package thinkingJava.PattAndMar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/26.
 */
public class ReplaceMentsTest {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        Pattern pattern = Pattern.compile("[Tt]");
        Matcher matcher = pattern.matcher("Ok,thank u very much!");
        matcher.find();
        matcher.appendReplacement(stringBuffer,"你好");
        System.out.println(stringBuffer);
        matcher.appendTail(stringBuffer);
        System.out.println(stringBuffer);
    }
}
