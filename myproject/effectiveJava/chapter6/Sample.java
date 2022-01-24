package effectiveJava.chapter6;

/**
 * Created by 15151 on 2020/2/8.
 * 测试标记注解test 测试程序在runest里T
 */
public class Sample {
    @Test
    public static void m1() {
    }//test should passed


    public static void m2() {
    }

    @Test
    public static void m3() {
        throw new RuntimeException("boom");
    }//test should fail

    public static void m4() {
    }

    @Test
    public  void m5() {
    }//invalid use non static method


    public static void m6() {
    }

    @Test
    public static void m7() {
        throw new RuntimeException("boom");
    }//test should fail

    public static void m8() {
    }

}
