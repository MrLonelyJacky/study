package com.jacky.springframework.beans.factory.config;

import com.jacky.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * bean的后处理器加入工厂
 */
public interface MyConfigurableBeanFactory extends HierarchicalBeanFactory, MySingletonBeanRegistry{
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(MyBeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
