package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;

import java.lang.reflect.Constructor;

/**
 * bean实例化策略接口
 */
public interface MyInstantiationStrategy {
    Object instantiate(MyBeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws MyBeansException;
}
