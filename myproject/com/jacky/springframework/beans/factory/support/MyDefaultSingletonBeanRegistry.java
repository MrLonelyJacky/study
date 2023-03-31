package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.factory.config.MySingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 单例注册表的默认实现
 * @author: jacky
 * @create: 2023-03-31 15:08
 **/
public  class MyDefaultSingletonBeanRegistry implements MySingletonBeanRegistry {
    private Map<String, Object> singletonObjects = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

}
