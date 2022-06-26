package spring.chapter7.introduce;

import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class JdkAutoConfigProxy implements InvocationHandler,AutoConfigProxy {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //todo 模拟链
        List<Object> objects = new ArrayList<>();
        IOtherInteImpl iOtherInte= new IOtherInteImpl();
        objects.add(iOtherInte);

        for (Object delegate:objects){
            Object retVal = AopUtils.invokeJoinpointUsingReflection(delegate, method, args);

            return retVal;
        }
        return null;
    }

    @Override
    public Object getProxy() {
        List<Object> objects = new ArrayList<>();
        IOtherInteImpl iOtherInte= new IOtherInteImpl();
        objects.add(iOtherInte);
        Set<Class<?>> allInterfacesSet = new LinkedHashSet<>();
        for (Object object:objects){
            Set<Class<?>> allInterfacesAsSet = ClassUtils.getAllInterfacesAsSet(object);
            allInterfacesSet.addAll(allInterfacesAsSet);
        }

        Object newProxyInstance = Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), ClassUtils.toClassArray(allInterfacesSet), this);
        return newProxyInstance;
    }
}
