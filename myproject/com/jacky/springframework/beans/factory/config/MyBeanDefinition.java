package com.jacky.springframework.beans.factory.config;

import com.jacky.springframework.beans.factory.PropertyValues;
import lombok.Data;

/**
 * @description: bean定义信息
 * @author: jacky
 * @create: 2023-03-31 14:16
 **/
@Data
public class MyBeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    public MyBeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public MyBeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
