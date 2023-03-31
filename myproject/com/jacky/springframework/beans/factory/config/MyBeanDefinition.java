package com.jacky.springframework.beans.factory.config;

import lombok.Data;

/**
 * @description: bean定义信息
 * @author: jacky
 * @create: 2023-03-31 14:16
 **/
@Data
public class MyBeanDefinition {
    private Class beanClass;

    public MyBeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

}
