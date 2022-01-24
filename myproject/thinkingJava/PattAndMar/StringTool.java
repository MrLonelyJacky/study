package thinkingJava.PattAndMar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/26.
 */
public class StringTool {
    //下划线转驼峰的匹配模式
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    //驼峰转下划线匹配模式
    private static Pattern humblePattern = Pattern.compile("[A-Z]");

    //下滑线转驼峰
    public static String lineToHumble(String format) {
        if (format == null || "".equals(format)) {
            return null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            Matcher matcher = linePattern.matcher(format);
            while (matcher.find()) {
                matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }

    }

    public static void main(String[] args) {
        System.out.println(lineToHumble("action_id"));
    }
}
