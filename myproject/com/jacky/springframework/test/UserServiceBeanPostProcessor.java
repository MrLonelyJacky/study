package com.jacky.springframework.test;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;

/**
 * @description:
 * @author: jacky
 * @create: 2023-04-03 16:18
 **/
public class UserServiceBeanPostProcessor implements MyBeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws MyBeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            System.out.println("开始增强了。。。。");
            userService.setName(userService.getName()+"增强了！");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws MyBeansException {
        return bean;
    }
}
