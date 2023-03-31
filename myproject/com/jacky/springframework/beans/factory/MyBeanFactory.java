package com.jacky.springframework.beans.factory;


import com.jacky.springframework.beans.MyBeansException;

/**
 *
 */
public interface MyBeanFactory {

    Object getBean(String name) throws MyBeansException;

    <T> T getBean(String name, Class<T> requiredType) throws MyBeansException;

}
