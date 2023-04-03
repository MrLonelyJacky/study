package com.jacky.springframework.test;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyConfigurableListableBeanFactory;
import com.jacky.springframework.beans.factory.PropertyValue;
import com.jacky.springframework.beans.factory.PropertyValues;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyBeanFactoryPostProcessor;

/**
 * @description:
 * @author: jacky
 * @create: 2023-04-03 17:12
 **/
public class UserServiceBeanFactoryProcessor implements MyBeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(MyConfigurableListableBeanFactory beanFactory) throws MyBeansException {
        MyBeanDefinition userService = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userService.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","segi"));
    }
}
