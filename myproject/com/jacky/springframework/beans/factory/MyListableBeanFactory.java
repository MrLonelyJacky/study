package com.jacky.springframework.beans.factory;


import com.jacky.springframework.beans.MyBeansException;

import java.util.Map;

/**
 * Extension of the {@link MyBeanFactory} interface to be implemented by bean factories
 * that can enumerate all their bean instances, rather than attempting bean lookup
 * by name one by one as requested by clients. BeanFactory implementations that
 * preload all their bean definitions (such as XML-based factories) may implement
 * this interface.
 * <p>
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface MyListableBeanFactory extends MyBeanFactory{

    /**
     * 按照类型返回 所有Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws MyBeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws MyBeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
