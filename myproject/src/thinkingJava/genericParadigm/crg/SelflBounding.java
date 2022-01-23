package thinkingJava.genericParadigm.crg;

/**
 * Created by 15151 on 2019/5/6.
 */
public class SelflBounding {
    public static void main(String[] args) {
        A a = new A();
        a.set(new A());
        a = a.set(new A()).get();
        C c = new C();
        c.setAndGet(new C());
        F f =new F();
        f.set(new F());
    }
}
