package com.jacky.springframework.test;

import com.jacky.springframework.aop.MyMethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeMapperAdvice implements MyMethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("-----------前置通知----------");
    }
}
