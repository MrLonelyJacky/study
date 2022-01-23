/*
package thinkingJava.String;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * thinkingJava.String 工具类
 * Created by crrcdt on 2016/12/07.
 *//*

public class StringUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    */
/**
     * 下划线转驼峰
     * @param str
     * @return
     *//*

    public static thinkingJava.String lineToHump(thinkingJava.String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    */
/**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     * @param str
     * @return
     *//*

    public static thinkingJava.String humpToLine(thinkingJava.String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    */
/**
     * 驼峰转下划线,效率比上面高
     * @param str
     * @return
     *//*

    public static thinkingJava.String humpToLine2(thinkingJava.String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    */
/**
     * 首字母转小写
     * @param s
     * @return
     *//*

    public static thinkingJava.String toLowerCaseFirstOne(thinkingJava.String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    */
/**
     * 首字母转大写
     * @param s
     * @return
     *//*

    public static thinkingJava.String toUpperCaseFirstOne(thinkingJava.String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    */
/**
     * object转String
     * @param object
     * @return
     *//*

    public static thinkingJava.String getString(Object object) {
        return getString(object, "");
    }

    public static thinkingJava.String getString(Object object, thinkingJava.String defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return object.toString();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    */
/**
     * object转Integer
     * @param object
     * @return
     *//*

    public static int getInt(Object object) {
        return getInt(object, -1);
    }

    public static int getInt(Object object, Integer defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    //数字转字母 1-26 ： A-Z(适用于excel排序顺序)
    public static thinkingJava.String numberToLetter(int num) {
        if (num <= 0) {
            return null;
        }
        thinkingJava.String letter = "";
        num--;
        do {
            if (letter.length() > 0) {
                num--;
            }
            letter = ((char) (num % 26 + (int) 'A')) + letter;
            num = (int) ((num - num % 26) / 26);
        } while (num > 0);

        return letter;
    }

    */
/**
     * object转Boolean
     * @param object
     * @return
     *//*

    public static boolean getBoolean(Object object) {
        return getBoolean(object, false);
    }

    public static boolean getBoolean(Object object, Boolean defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static thinkingJava.String getSqlOrderFromSort(thinkingJava.String sort){
        thinkingJava.String result="";
        if(StringUtils.isBlank(sort)){
            return result;
        }
        sort = humpToLine2(sort);
        // sort:格式 +aa,-bb,+cc
        List<thinkingJava.String> lsSort =new ArrayList<>();

        thinkingJava.String[] sortFields = sort.split(",");
        for(thinkingJava.String item :sortFields ) {
            if (item.trim().indexOf("descend" ) == 0) {
                // 降序
                lsSort.add(item.replace("descend", "" ).trim() + " DESC" );
            } else {
                // 升序
                lsSort.add(item.replace("ascend", "" ).trim() + " ASC" );
            }
        }
        // 转成SQL order字符串
        result  =StringUtils.join(lsSort,",");

        return result;
    }
    */
/**
     * 转大写
     *//*

    public static char charToUpperCase(char ch){
        if(ch <= 122 && ch >= 97){
            ch -= 32;
        }
        return ch;
    }
    */
/**
     *转小写
     *//*

    public static char charToLowerCase(char ch){
        if(ch <= 90 && ch >= 65){
            ch += 32;
        }
        return ch;
    }
    */
/**
     * 字符串转大写
     *
     **//*

    public static thinkingJava.String convertString(thinkingJava.String str){
        char[] ch = str.toCharArray();
        StringBuffer sbf = new StringBuffer();
        for(int i=0; i< ch.length; i++){
            if(ch[i]>=97){
                sbf.append(charToUpperCase(ch[i]));
            }else {
                sbf.append(ch[i]);
            }
        }
        return sbf.toString();
    }
    */
/**字符串转大写**//*

    public static void main(thinkingJava.String[] args) {
        Integer msg = 100;
        thinkingJava.String msg1 = numberToLetter(msg);
        System.out.println(msg1);
    }
}

*/
