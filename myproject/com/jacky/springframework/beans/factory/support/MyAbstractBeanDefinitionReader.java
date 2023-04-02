package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.core.io.DefaultResourceLoader;
import com.jacky.springframework.core.io.MyResourceLoader;

/**
 * @description:抽象类把 BeanDefinitionReader 接口的前两个方法全部实现完了，并提供了构造函数，让外部的调用使用方，把Bean定义注入类，传递进来。
 * 这样在接口 BeanDefinitionReader 的具体实现类中，就可以把解析后的 XML 文件中的 Bean 信息，注册到 Spring 容器去了。以前我们是通过单元测试使用，调用 BeanDefinitionRegistry 完成Bean的注册，现在可以放到 XMl 中操作了
 * @author: jacky
 * @create: 2023-04-02 14:35
 **/
public abstract class MyAbstractBeanDefinitionReader implements MyBeanDefinitionReader{
    private final MyBeanDefinitionRegistry registry;

    private MyResourceLoader resourceLoader;

    protected MyAbstractBeanDefinitionReader(MyBeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public MyAbstractBeanDefinitionReader(MyBeanDefinitionRegistry registry, MyResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public MyBeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public MyResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
