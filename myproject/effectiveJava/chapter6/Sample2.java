package effectiveJava.chapter6;

/**
 * Created by 15151 on 2020/2/10.
 */
public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {
        int i = 0;
        i = i / i;
    }//test should pass
    @ExceptionTest(ArithmeticException.class)
    public static void m2() {
        int[] a = new int[0];
        int i = a[1];
    }//test should fail wrong exception
    @ExceptionTest(ArithmeticException.class)
    public static void m3() {
    }//fail no exception
}
