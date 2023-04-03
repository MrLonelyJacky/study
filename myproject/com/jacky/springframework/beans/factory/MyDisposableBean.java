package com.jacky.springframework.beans.factory;

/**
 * @description:
 * @author: jacky
 * @create: 2023-04-03 18:02
 **/
public interface MyDisposableBean {

    void destroy() throws Exception;
}
