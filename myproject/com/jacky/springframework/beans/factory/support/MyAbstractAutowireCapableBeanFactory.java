package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;

/**
 * @description: 自动装配的工厂
 * @author: jacky
 * @create: 2023-03-31 15:44
 **/
public abstract class MyAbstractAutowireCapableBeanFactory extends MyAbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, MyBeanDefinition beanDefinition) throws MyBeansException {
        Object bean = null;
        try {
          bean =  beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        addSingleton(beanName, bean);
        return bean;
    }


}
