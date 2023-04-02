package com.jacky.springframework.beans.factory.config;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyConfigurableListableBeanFactory;

/**
 * bean定义信息后处理器
 */
public interface MyBeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws MyBeansException
     */
    void postProcessBeanFactory(MyConfigurableListableBeanFactory beanFactory) throws MyBeansException;
}
