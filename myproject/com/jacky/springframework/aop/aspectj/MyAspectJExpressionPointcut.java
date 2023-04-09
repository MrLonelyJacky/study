package com.jacky.springframework.aop.aspectj;

import com.jacky.springframework.aop.MyClassFilter;
import com.jacky.springframework.aop.MyMethodMatcher;
import com.jacky.springframework.aop.MyPointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 切点表达式实现了 Pointcut、ClassFilter、MethodMatcher，三个接口定义方法，同时这个类主要是对 aspectj 包提供的表达式校验方法使用。
 * 匹配 matches：pointcutExpression.couldMatchJoinPointsInType(clazz)、pointcutExpression.matchesMethodExecution(method).alwaysMatches()，
 * 这部分内容可以单独测试验证。
 */
public class MyAspectJExpressionPointcut implements MyPointcut, MyClassFilter, MyMethodMatcher {
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;


    public MyAspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public MyClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MyMethodMatcher getMethodMatcher() {
        return this;
    }
}
