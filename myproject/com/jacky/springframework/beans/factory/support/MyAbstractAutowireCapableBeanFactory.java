package com.jacky.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.*;
import com.jacky.springframework.beans.factory.config.MyAutowireCapableBeanFactory;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;
import com.jacky.springframework.beans.factory.config.MyBeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @description: 自动装配的工厂
 * @author: jacky
 * @create: 2023-03-31 15:44
 **/
public abstract class MyAbstractAutowireCapableBeanFactory extends MyAbstractBeanFactory implements MyAutowireCapableBeanFactory {

    private MyInstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, MyBeanDefinition beanDefinition) throws MyBeansException {
        Object bean;
        try {
            bean = instantiationStrategy.instantiate(beanDefinition, beanName, null, null);
            applyPropertyValues(beanName, bean, beanDefinition);
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new MyBeansException("Instantiation of bean failed", e);
        }
        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        // 判断 SCOPE_SINGLETON、SCOPE_PROTOTYPE
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
        return bean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, MyBeanDefinition beanDefinition) {
        if (bean instanceof MyDisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new MyDisposableBeanAdapter(bean, beanName, beanDefinition));
        }
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
        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
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

    private Object initializeBean(String beanName, Object bean, MyBeanDefinition beanDefinition)  {
        // invokeAwareMethods
        if (bean instanceof MyAware) {
            if (bean instanceof MyBeanFactoryAware) {
                ((MyBeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof MyBeanClassLoaderAware){
                ((MyBeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof MyBeanNameAware) {
                ((MyBeanNameAware) bean).setBeanName(beanName);
            }
        }
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new MyBeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, MyBeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        if (wrappedBean instanceof MyInitializingBean) {
            ((MyInitializingBean) wrappedBean).afterPropertiesSet();
        }

        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new MyBeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(wrappedBean);
        }
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
