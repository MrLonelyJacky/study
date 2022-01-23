package thinkingJava.innerClass.callback;

/**
 * Created by 15151 on 2019/4/9.
 */
public class MyIncrement {
    public void increment() {
        System.out.println("other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment();
    }
}
