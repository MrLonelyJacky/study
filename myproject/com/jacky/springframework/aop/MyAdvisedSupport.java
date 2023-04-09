package com.jacky.springframework.aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 包装切面通知信息
 * AdvisedSupport，主要是用于把代理、拦截、匹配的各项属性包装到一个类中，方便在 Proxy 实现类进行使用。这和你的业务开发中包装入参是一个道理
 * TargetSource，是一个目标对象，在目标对象类中提供 Object 入参属性，以及获取目标类 TargetClass 信息。
 * MethodInterceptor，是一个具体拦截方法实现类，由用户自己实现 MethodInterceptor#invoke 方法，做具体的处理。像我们本文的案例中是做方法监控处理
 * MethodMatcher，是一个匹配方法的操作，这个对象由 AspectJExpressionPointcut 提供服务。
 */
@Data
public class MyAdvisedSupport {
    // 被代理的目标对象
    private TargetSource targetSource;
    // 方法拦截器
    private MethodInterceptor methodInterceptor;
    // 方法匹配器(检查目标方法是否符合通知条件)
    private MyMethodMatcher methodMatcher;
}
