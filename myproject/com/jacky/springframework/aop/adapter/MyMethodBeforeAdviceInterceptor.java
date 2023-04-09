package com.jacky.springframework.aop.adapter;

import com.jacky.springframework.aop.MyMethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *  方法拦截器
 *  MethodBeforeAdviceInterceptor 实现了 MethodInterceptor 接口，在 invoke 方法中调用 advice 中的 before 方法，传入对应的参数信息。
 * 而这个 advice.before 则是用于自己实现 MethodBeforeAdvice 接口后做的相应处理。其实可以看到具体的 MethodInterceptor 实现类，
 * 其实和我们之前做的测试是一样的，只不过现在交给了 Spring 来处理
 */
public class MyMethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MyMethodBeforeAdvice advice;

    public MyMethodBeforeAdviceInterceptor(MyMethodBeforeAdvice advice) {
        this.advice = advice;
    }

    public MyMethodBeforeAdvice getAdvice() {
        return advice;
    }

    public void setAdvice(MyMethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
