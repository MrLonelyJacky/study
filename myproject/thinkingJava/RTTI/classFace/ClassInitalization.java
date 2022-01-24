package thinkingJava.RTTI.classFace;

import java.util.Random;

/**
 * Created by 15151 on 2019/4/19.
 */
public class ClassInitalization {
    public static Random random = new Random(47);

    public static void main(String[] args) throws ClassNotFoundException {
        Class initable = Initable.class;
        System.out.println("After creating initable");
        //这里要强调下常量即使在Initabble没有初始化的前提下也可以被读取
        System.out.println(Initable.staticFianl);
        System.out.println(Initable.staticFianl2);
        System.out.println(Initable2.staticNonFinal);
        Class initable3= Class.forName("thinkingJava.RTTI.classFace.Initable3");
        System.out.println("After creating initable3");
        System.out.println(Initable3.staticNonFinal);
    }
}
