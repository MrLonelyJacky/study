package thinkingJava.innerClass.callback;

/**
 * Created by 15151 on 2019/4/9.
 */
public class Callbacks {
    public static void main(String[] args) {
        Callee1 callee1 = new Callee1();
        Callee2 callee2 = new Callee2();
        MyIncrement.f(callee2);
        Caller caller1 = new Caller(callee1);
        caller1.go();
        caller1.go();
        Caller caller2 = new Caller(callee2.getCallback());
        caller2.go();
        caller2.go();
    }
}
