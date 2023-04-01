package com.jacky.springframework.beans.factory;


import com.jacky.springframework.beans.MyBeansException;

/**
 * bean工厂 获取bean
 */
public interface MyBeanFactory {

    Object getBean(String name) throws MyBeansException;

    /*<T> T getBean(String name, Class<T> requiredType) throws MyBeansException;*/

    /**
     * based on args to get bean 参数 构造方法
     * @param name
     * @param args
     * @return
     * @throws MyBeansException
     */
    Object getBean(String name, Object... args) throws MyBeansException;
}
