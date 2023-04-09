package com.jacky.springframework.beans.factory.config;

import com.jacky.springframework.beans.MyBeansException;

public interface MyInstantiationAwareBeanPostProcessor extends MyBeanPostProcessor{
    /**
     * 实例化前增强
     * @param beanClass
     * @param beanName
     * @return
     * @throws MyBeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws MyBeansException;
}
