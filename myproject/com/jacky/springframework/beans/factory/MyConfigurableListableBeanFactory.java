package com.jacky.springframework.beans.factory;


import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyAutowireCapableBeanFactory;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyConfigurableBeanFactory;
import org.springframework.beans.BeansException;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link }, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface MyConfigurableListableBeanFactory extends MyListableBeanFactory, MyAutowireCapableBeanFactory, MyConfigurableBeanFactory {

    MyBeanDefinition getBeanDefinition(String beanName) throws MyBeansException;

    void preInstantiateSingletons() throws MyBeansException;

}
