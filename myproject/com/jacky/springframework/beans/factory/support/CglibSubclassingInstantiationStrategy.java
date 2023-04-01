package com.jacky.springframework.beans.factory.support;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * cglib 实例化
 */
public class CglibSubclassingInstantiationStrategy implements MyInstantiationStrategy {
    @Override
    public Object instantiate(MyBeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws MyBeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (ctor == null) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
