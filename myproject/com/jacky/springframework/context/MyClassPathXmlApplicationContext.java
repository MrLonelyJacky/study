package com.jacky.springframework.context;

import com.jacky.springframework.beans.MyBeansException;

/**
 * @description:
 * @author: jacky
 * @create: 2023-04-03 11:08
 **/
public class MyClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public MyClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws
     */
    public MyClassPathXmlApplicationContext(String... configLocations) throws MyBeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }


}
