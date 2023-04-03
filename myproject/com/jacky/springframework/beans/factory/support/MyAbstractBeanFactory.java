package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;
import com.jacky.springframework.beans.factory.config.MyConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 拥有强大功能的模板 bean工厂
 * @author: jacky
 * @create: 2023-03-31 14:42
 **/
public abstract class MyAbstractBeanFactory extends MyDefaultSingletonBeanRegistry implements MyConfigurableBeanFactory {

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<MyBeanPostProcessor> beanPostProcessors = new ArrayList<>();

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

    @Override
    public Object getBean(String name, Object... args) throws MyBeansException {
        //首先从单例注册表中获取，如果有则直接返回
        Object singleton = getSingleton(name);
        if (singleton != null) {
            return singleton;
        }
        //获取bean的定义信息 并创建bean
        MyBeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }

    protected abstract MyBeanDefinition getBeanDefinition(String beanName) throws MyBeansException;

    protected abstract Object createBean(String beanName, MyBeanDefinition beanDefinition) throws MyBeansException;

    protected abstract Object createBean(String beanName, MyBeanDefinition beanDefinition, Object[] args) throws MyBeansException;

    public List<MyBeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    @Override
    public void addBeanPostProcessor(MyBeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
