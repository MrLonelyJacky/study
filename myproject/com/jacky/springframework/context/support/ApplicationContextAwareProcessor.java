package com.jacky.springframework.context.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;
import com.jacky.springframework.context.MyApplicationContext;
import com.jacky.springframework.context.MyApplicationContextAware;

/**
 * @description: 由于 ApplicationContext 的获取并不能直接在创建 Bean 时候就可以拿到，所以需要在 refresh 操作时，把 ApplicationContext
 * 写入到一个包装的 BeanPostProcessor 中去，再由 AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization 方法调用。
 * @author: jacky
 * @create: 2023-04-04 15:14
 **/
public class ApplicationContextAwareProcessor implements MyBeanPostProcessor {

    private final MyApplicationContext myApplicationContext;

    public ApplicationContextAwareProcessor(MyApplicationContext myApplicationContext) {
        this.myApplicationContext = myApplicationContext;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws MyBeansException {
        if (bean instanceof MyApplicationContextAware){
            ((MyApplicationContextAware) bean).setApplicationContext(myApplicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws MyBeansException {
        return bean;
    }
}
