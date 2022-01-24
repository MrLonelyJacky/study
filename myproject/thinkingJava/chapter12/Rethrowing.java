package thinkingJava.chapter12;

/**
 * Created by 15151 on 2020/3/19.
 */
public class Rethrowing {
    public static void f() throws Exception {
        System.out.println("origin exception in f");
        throw new Exception("f throws");
    }
    public static void g(){
        try {
            f();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
