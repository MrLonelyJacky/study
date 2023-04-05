package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyFactoryBean;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;
import com.jacky.springframework.beans.factory.config.MyConfigurableBeanFactory;
import com.jacky.springframework.util.ClassUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: 拥有强大功能的模板 bean工厂
 * @author: jacky
 * @create: 2023-03-31 14:42
 **/
public abstract class MyAbstractBeanFactory extends MyFactoryBeanRegistrySupport implements MyConfigurableBeanFactory {
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<MyBeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws MyBeansException {

        return  doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws MyBeansException {
        return doGetBean(name, args);
    }

    protected abstract MyBeanDefinition getBeanDefinition(String beanName) throws MyBeansException;

    protected abstract Object createBean(String beanName, MyBeanDefinition beanDefinition) throws MyBeansException;

    protected abstract Object createBean(String beanName, MyBeanDefinition beanDefinition, Object[] args) throws MyBeansException;

    public List<MyBeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    @Override
    public void addBeanPostProcessor(MyBeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        MyBeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof MyFactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            MyFactoryBean<?> factoryBean = (MyFactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }
}
