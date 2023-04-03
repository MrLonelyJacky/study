package com.jacky.springframework.beans.factory;

/**
 * @description:
 * @author: jacky
 * @create: 2023-04-03 18:01
 **/
public interface MyInitializingBean {
    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
