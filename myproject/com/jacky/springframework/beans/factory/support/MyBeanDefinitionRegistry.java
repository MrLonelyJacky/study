package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;

/**
 * @description: brsn定义信息注册表
 * @author: jacky
 * @create: 2023-03-31 17:40
 **/
public interface MyBeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, MyBeanDefinition beanDefinition);

    /**
     * 使用Bean名称查询BeanDefinition
     *
     * @param beanName
     * @return
     * @throws MyBeansException
     */
    MyBeanDefinition getBeanDefinition(String beanName) throws MyBeansException;

    /**
     * 是否包含定义信息
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
