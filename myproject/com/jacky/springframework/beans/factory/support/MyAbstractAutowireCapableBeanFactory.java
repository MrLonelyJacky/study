package com.jacky.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.PropertyValue;
import com.jacky.springframework.beans.factory.PropertyValues;
import com.jacky.springframework.beans.factory.config.MyAutowireCapableBeanFactory;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;
import com.jacky.springframework.beans.factory.config.MyBeanReference;

import java.lang.reflect.Constructor;

/**
 * @description: 自动装配的工厂
 * @author: jacky
 * @create: 2023-03-31 15:44
 **/
public abstract class MyAbstractAutowireCapableBeanFactory extends MyAbstractBeanFactory implements MyAutowireCapableBeanFactory {

    private MyInstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, MyBeanDefinition beanDefinition) throws MyBeansException {
        Object bean = instantiationStrategy.instantiate(beanDefinition, beanName, null, null);
        applyPropertyValues(beanName, bean, beanDefinition);
        addSingleton(beanName, bean);
        return bean;
    }

    @Override
    protected Object createBean(String beanName, MyBeanDefinition beanDefinition, Object[] args) throws MyBeansException {
        Object bean;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行bean的初始化方法，和 初始化前后的处理器
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new MyBeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws MyBeansException {
        Object result = existingBean;
        for (MyBeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws MyBeansException {
        Object result = existingBean;
        for (MyBeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    private Object initializeBean(String beanName, Object bean, MyBeanDefinition beanDefinition) {
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, MyBeanDefinition beanDefinition) {
    }

    private Object createBeanInstance(String beanName, MyBeanDefinition beanDefinition, Object[] args) {
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
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 填充属性
     *
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
