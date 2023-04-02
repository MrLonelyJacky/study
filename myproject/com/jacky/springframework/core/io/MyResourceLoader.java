package com.jacky.springframework.core.io;

/**
 * @description: 按照资源加载的不同方式，资源加载器可以把这些方式集中到统一的类服务下进行处理，外部用户只需要传递资源地址即可，简化使用。
 * @author: jacky
 * @create: 2023-04-02 13:37
 **/
public interface MyResourceLoader {
    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    MyResource getResource(String location);

}
