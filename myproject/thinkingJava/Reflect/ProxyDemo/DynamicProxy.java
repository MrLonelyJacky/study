package thinkingJava.Reflect.ProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 15151 on 2019/4/27.
 * 动态代理类
 */
public class DynamicProxy implements InvocationHandler {
    //代理的真实对象
    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理类在代理前做些操作
        System.out.println("before rent house");
        System.out.println("Method:" + method);
        // 代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object invoke = method.invoke(subject, args);
        System.out.println("after rent house i want to say goodbye to u");
        return invoke;
    }
}
