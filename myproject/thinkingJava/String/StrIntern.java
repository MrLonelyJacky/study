package thinkingJava.String;

/**
 * Created by 15151 on 2019/5/15.
 * 很明显new String是在堆上创建对象而非常量池
 */
public class StrIntern {
    /*final static thinkingJava.String thank = "thank";
    final static thinkingJava.String you = "u";*/

    public static void main(String[] args) {
        String a = new String("a");
        String a2 = new String("a");
        System.out.println(a == a2);
        String str2 = new String("str") + new String("2");
        //调用该方法将会在常量池中生成一个对堆中str2的引用
        str2.intern();
        //这时候常量池中有str2存在的引用，直接返回改引用
        String s1 = "str2";
        System.out.println(s1 == str2);
        final  String thank = "thank";
        final  String you = "u";
        String thanku = thank + you;
        String thankyou = "thanku";
        System.out.println(thanku == thankyou);
    }
}
