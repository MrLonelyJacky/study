package com.jacky.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.PropertyValue;
import com.jacky.springframework.beans.factory.PropertyValues;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyBeanReference;

import java.lang.reflect.Constructor;

/**
 * @description: 自动装配的工厂
 * @author: jacky
 * @create: 2023-03-31 15:44
 **/
public abstract class MyAbstractAutowireCapableBeanFactory extends MyAbstractBeanFactory{

    private MyInstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, MyBeanDefinition beanDefinition) throws MyBeansException {
        Object bean =  instantiationStrategy.instantiate(beanDefinition,beanName,null,null);
        applyPropertyValues(beanName,bean,beanDefinition);
        addSingleton(beanName, bean);
        return bean;
    }

    @Override
    protected Object createBean(String beanName, MyBeanDefinition beanDefinition, Object[] args) throws MyBeansException {
        Class beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        //找到对应的构造器
        Constructor constructorToUse = null;
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        Object bean =  instantiationStrategy.instantiate(beanDefinition,beanName,constructorToUse,args);
        applyPropertyValues(beanName,bean,beanDefinition);
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 填充属性
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, MyBeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

            String name = propertyValue.getName();
            Object value = propertyValue.getValue();

            if (value instanceof MyBeanReference) {
                // A 依赖 B，获取 B 的实例化
                MyBeanReference beanReference = (MyBeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 属性填充
            BeanUtil.setFieldValue(bean, name, value);
        }
    }
}
