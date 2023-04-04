package com.jacky.springframework.beans.factory;

import com.jacky.springframework.beans.MyBeansException;

/**
 * @description: 实现此接口，既能感知到所属的 BeanFactory #3.2 BeanClassLoaderAware
 * @author: jacky
 * @create: 2023-04-04 15:07
 **/
public interface MyBeanFactoryAware extends MyAware{

    void setBeanFactory(MyBeanFactory beanFactory) throws MyBeansException;
}
