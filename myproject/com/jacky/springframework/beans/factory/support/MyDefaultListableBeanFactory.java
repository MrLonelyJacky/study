package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyConfigurableListableBeanFactory;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:  提供获取和注册 bean定义功能
 * @author: jacky
 * @create: 2023-03-31 16:03
 **/
public class MyDefaultListableBeanFactory extends MyAbstractAutowireCapableBeanFactory implements MyBeanDefinitionRegistry, MyConfigurableListableBeanFactory {

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
    public void preInstantiateSingletons() throws MyBeansException {

    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws MyBeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }


}
