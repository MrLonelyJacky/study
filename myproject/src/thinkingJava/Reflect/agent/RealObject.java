package thinkingJava.Reflect.agent;

/**
 * Created by 15151 on 2019/4/25.
 */
public class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("do sth");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("do sth else" + arg);
    }
}
