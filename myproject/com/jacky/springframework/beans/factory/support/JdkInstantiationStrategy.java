package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * jdk实例化
 */
class JdkInstantiationStrategy implements MyInstantiationStrategy {
    @Override
    public Object instantiate(MyBeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws MyBeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if (ctor != null) {
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return beanClass.getDeclaredConstructor().newInstance();
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new MyBeansException("fail to instantiate [" + beanClass.getName() + "]", e);
        }

    }
}
