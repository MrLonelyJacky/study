package thinkingJava.Reflect.agent;

import java.lang.reflect.Proxy;

/**
 * Created by 15151 on 2019/4/25.
 */
public class SimpleDynamicProxy {
    public static void consume(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonbo");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consume(realObject);
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(realObject));
        consume(proxy);
    }
}
