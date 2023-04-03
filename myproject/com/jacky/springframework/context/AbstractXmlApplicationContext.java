package com.jacky.springframework.context;

import com.jacky.springframework.beans.factory.support.MyDefaultListableBeanFactory;
import com.jacky.springframework.beans.factory.support.MyXmlBeanDefinitionReader;
import com.jacky.springframework.core.io.MyResource;

/**
 * @description: 在AbstractXmlApplicationContext 抽象类的 loadBeanDefinitions 方法实现中，使用 XmlBeanDefinitionReader 类，
 * 处理了关于 XML 文件配置信息的操作。
 * 同时这里又留下了一个抽象类方法，getConfigLocations()，此方法是为了从入口上下文类，拿到配置信息的地址描述。
 * @author: jacky
 * @create: 2023-04-03 11:01
 **/
public abstract class AbstractXmlApplicationContext extends MyAbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(MyDefaultListableBeanFactory beanFactory) {
        MyXmlBeanDefinitionReader xmlBeanDefinitionReader = new MyXmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        for (String location : configLocations){
            MyResource resource = getResource(location);
            xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        }

    }

    protected abstract String[] getConfigLocations();
}
