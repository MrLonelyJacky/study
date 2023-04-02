package com.jacky.springframework.beans.factory.config;

import com.jacky.springframework.beans.MyBeansException;

/**
 * 在 Spring 源码中有这样一段描述 Factory hook that allows for custom modification of new bean instances,e.g.
 * checking for marker interfaces or wrapping them with proxies.也就是提供了修改新实例化 Bean 对象的扩展点。
 * 另外此接口提供了两个方法：postProcessBeforeInitialization 用于在 Bean 对象执行初始化方法之前，执行此方法、
 * postProcessAfterInitialization用于在 Bean 对象执行初始化方法之后，执行此方法。
 */
public interface MyBeanPostProcessor {
    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws MyBeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws MyBeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws MyBeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws MyBeansException;
}
