package thinkingJava.Reflect.ProxyDemo;

import thinkingJava.Reflect.agent.Interface;
import thinkingJava.Reflect.agent.RealObject;

import java.lang.reflect.Proxy;

/**
 * Created by 15151 on 2019/4/27.
 * 动态代理的好处就是相对静态来说，可以代理不同的被代理类
 * 下面的Subject和Interface两个不同的被代理
 */
public class Client {
    public static void main(String[] args) {
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class},
                new DynamicProxy(new RentSubject()));
        proxyInstance.hell0("please rent my house");
        proxyInstance.rent();
        Interface instance = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class},
                new DynamicProxy(new RealObject()));
        instance.doSomething();
        instance.somethingElse("hei hei");

    }
}
