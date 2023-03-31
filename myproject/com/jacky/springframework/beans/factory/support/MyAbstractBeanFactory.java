package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyBeanFactory;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;

/**
 * @description: 拥有强大功能的模板 bean工厂
 * @author: jacky
 * @create: 2023-03-31 14:42
 **/
public abstract class MyAbstractBeanFactory extends MyDefaultSingletonBeanRegistry implements MyBeanFactory {

    @Override
    public Object getBean(String name) throws MyBeansException {
        //首先从单例注册表中获取，如果有则直接返回
        Object singleton = getSingleton(name);
        if (singleton != null) {
            return singleton;
        }
        //获取bean的定义信息 并创建bean
        MyBeanDefinition beanDefinition = getBeanDefinition(name);

        return createBean(name, beanDefinition);
    }

    protected abstract MyBeanDefinition getBeanDefinition(String beanName) throws MyBeansException;

    protected abstract Object createBean(String beanName, MyBeanDefinition beanDefinition) throws MyBeansException;
}
