package com.jacky.springframework.beans.factory.config;


import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyBeanFactory;

/**
 * Extension of the
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 * 是一个自动化处理Bean工厂配置的接口
 */
public interface MyAutowireCapableBeanFactory extends MyBeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws MyBeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws MyBeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws MyBeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws MyBeansException;

}
