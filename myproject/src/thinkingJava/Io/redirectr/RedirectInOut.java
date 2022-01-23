package thinkingJava.Io.redirectr;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by 15151 on 2019/5/11.
 */
public class RedirectInOut {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(System.out);
        PrintStream printStream = System.out;// 保存原输出流，这步很重要，不然就恢复不回来
        PrintStream ps = new PrintStream("输出重定向.txt");// 创建文件输出流
        System.setOut(ps);
        System.out.println(System.out);
        int age = 18;
        System.out.println("age:" + age);
        String s = "我的小弟么";
        System.out.println(s);
        System.setOut(printStream); // 恢复原有的输出流
        System.out.println("日志已经保存到文件中");
    }
}
