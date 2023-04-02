package com.jacky.springframework.context;

import com.jacky.springframework.beans.MyBeansException;

/**
 * ConfigurableApplicationContext 继承自 ApplicationContext，并提供了 refresh 这个核心方法。如果你有看过一些 Spring 源码，那么一定会看到这个方法。 接下来也是需要在上下文的实现中完成刷新容器的操作过程。
 */
public interface ConfigurableApplicationContext extends MyApplicationContext{
    /**
     * 刷新容器
     *
     * @throws MyBeansException
     */
    void refresh() throws MyBeansException;

}
