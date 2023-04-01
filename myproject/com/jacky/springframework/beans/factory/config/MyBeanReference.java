package com.jacky.springframework.beans.factory.config;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * Bean 的引用
 */
public class MyBeanReference {

    private final String beanName;

    public MyBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
