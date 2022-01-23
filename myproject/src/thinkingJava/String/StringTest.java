/*
package thinkingJava.String;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * Created by 15151 on 2019/1/24.
 *//*

public class StringTest {
    public static void main(thinkingJava.String[] args) {
        thinkingJava.String project_name = StringUtil.lineToHump("PROJECT_NAME");
        System.out.println(project_name);
        thinkingJava.String projectName = StringUtil.humpToLine("projectName");
        System.out.println(projectName);
        thinkingJava.String a1= "a\\w";
        System.out.println(a1.length());
        Pattern pattern = Pattern.compile(a1);
        Matcher matcher = pattern.matcher("aabbb");
        //System.out.println(matcher.find());
        while(matcher.find()){
            System.out.println("hahha");
        }
        */
/*thinkingJava.String aa = "ab";
        System.out.println(aa.indexOf('-'));
        System.out.println(3/2);*//*

        thinkingJava.String cc = "a-b";
        thinkingJava.String[] split = cc.split("/");
        for (int i = 0;i<split.length;i++){
            System.out.println(split[i]);
            System.out.println(split.length);
        }
        System.out.println("haha/haha".split("/").length);
        Pattern pattern1 =Pattern.compile("[(as)]");

        System.out.println(pattern.matcher("(").matches());
        Pattern patternSql = Pattern.compile("[()|\\s]+");
        System.out.println(patternSql.matcher(" ").matches());

    }

}
*/
