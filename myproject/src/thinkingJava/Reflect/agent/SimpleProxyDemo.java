package thinkingJava.Reflect.agent;

/**
 * Created by 15151 on 2019/4/25.
 */
public class SimpleProxyDemo {
    public static void consume(Interface who){
        who.doSomething();
        who.somethingElse("bubbo");
    }
    public static void main(String[] args) {
        consume(new RealObject());
        consume(new SimplyParoxy(new RealObject()));
    }
}
