package com.jacky.springframework.beans.factory;

/**
 * @description: 实现此接口，既能感知到所属的 ClassLoader
 * @author: jacky
 * @create: 2023-04-04 15:12
 **/
public interface MyBeanClassLoaderAware extends MyAware{
    void setBeanClassLoader(ClassLoader classLoader);
}
