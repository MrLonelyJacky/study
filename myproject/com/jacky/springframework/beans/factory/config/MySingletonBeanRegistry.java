package com.jacky.springframework.beans.factory.config;

/**
 *
 *
 * 单例注册表
 */
public interface MySingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
                                                