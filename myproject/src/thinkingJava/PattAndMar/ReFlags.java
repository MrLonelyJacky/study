package thinkingJava.PattAndMar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/26.
 */
public class ReFlags {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^Java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher("java has regex\njava has regex\n"
                +"JAVA has regular expressions\n"
                +"Regular expressions are in java");
        while (matcher.find()){
            System.out.println(matcher.group());
        }

    }
}
