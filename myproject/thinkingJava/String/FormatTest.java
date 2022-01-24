package thinkingJava.String;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 15151 on 2019/3/25.
 */
public class FormatTest {
    public static void main(String[] args) {
        int  value = 101;
        System.out.println(String.format("%02d",value));
        String raw = "hello";
        System.out.println(String.format("%7s",raw));
        String column = "name";
        System.out.println("name地址"+column.hashCode());
        System.out.println(String.format("%s = {0}",column));
        String sqlStr = "name = {0}";
        System.out.println(String.format("{%s}",1));
        System.out.println(sqlStr.replace("name","haha"));
        sqlStr.toUpperCase();
        column = "bb";
        System.out.println("bb地址"+column.hashCode());
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuilder2.append("a").append("c"));
        String a = "a";
        String b = "a";
        System.out.println(a.hashCode()+":"+b.hashCode());//
        String format = String.format("{%s}","1");
        System.out.println(format);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.incrementAndGet());
    }
}
