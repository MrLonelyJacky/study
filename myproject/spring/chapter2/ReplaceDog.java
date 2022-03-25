package spring.chapter2;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class ReplaceDog implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("hello i am a white dog");
        return obj;
    }
}
