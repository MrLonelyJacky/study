package com.jacky.springframework.context;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyConfigurableListableBeanFactory;
import com.jacky.springframework.beans.factory.support.MyBeanDefinitionReader;
import com.jacky.springframework.beans.factory.support.MyDefaultListableBeanFactory;
import com.jacky.springframework.beans.factory.support.MyXmlBeanDefinitionReader;
import com.jacky.springframework.core.io.MyResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description:在 refreshBeanFactory() 中主要是获取了 DefaultListableBeanFactory 的实例化以及对资源配置的加载操作
 * loadBeanDefinitions(beanFactory)，在加载完成后即可完成对 spring.xml 配置文件中 Bean 对象的定义和注册，
 * 同时也包括实现了接口 BeanFactoryPostProcessor、BeanPostProcessor 的配置 Bean 信息。
 * 但此时资源加载还只是定义了一个抽象类方法 loadBeanDefinitions(DefaultListableBeanFactory beanFactory)，继续由其他抽象类继承实现。
 * @author: jacky
 * @create: 2023-04-03 09:22
 **/
public abstract class MyAbstractRefreshableApplicationContext extends MyAbstractApplicationContext {

    private MyDefaultListableBeanFactory beanFactory;


    /**
     * 创建默认工厂 并加载bean定义信息
     *
     * @throws MyBeansException
     */
    @Override
    protected void refreshBeanFactory() throws MyBeansException {
        MyDefaultListableBeanFactory factory = createBeanFactory();
        loadBeanDefinitions(factory);
        this.beanFactory = factory;
    }

    private MyDefaultListableBeanFactory createBeanFactory() {
        return new MyDefaultListableBeanFactory();
    }

    @Override
    public MyConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected abstract void loadBeanDefinitions(MyDefaultListableBeanFactory beanFactory);
}
