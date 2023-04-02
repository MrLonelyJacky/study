package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.core.io.MyResource;
import com.jacky.springframework.core.io.MyResourceLoader;

/**
 * @description: 这是一个 Simple interface for bean definition readers.
 * 其实里面无非定义了几个方法，包括：getRegistry()、getResourceLoader()，以及三个加载Bean定义的方法
 * 。 这里需要注意 getRegistry()、getResourceLoader()，都是用于提供给后面三个方法的工具，加载和注册，
 * 这两个方法的实现会包装到抽象类中，以免污染具体的接口实现方法
 * @author: jacky
 * @create: 2023-04-02 13:40
 **/
public interface MyBeanDefinitionReader {
    MyBeanDefinitionRegistry getRegistry();

    MyResourceLoader getResourceLoader();

    void loadBeanDefinitions(MyResource resource) throws MyBeansException;

    void loadBeanDefinitions(MyResource... resources) throws MyBeansException;

    void loadBeanDefinitions(String location) throws MyBeansException;
}
