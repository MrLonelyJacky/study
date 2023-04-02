package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:  提供获取和注册 bean定义功能
 * @author: jacky
 * @create: 2023-03-31 16:03
 **/
public class MyDefaultListableBeanFactory extends MyAbstractAutowireCapableBeanFactory implements MyBeanDefinitionRegistry{

    private Map<String, MyBeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public void registerBeanDefinition(String beanName, MyBeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public MyBeanDefinition getBeanDefinition(String beanName) throws MyBeansException {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

}
