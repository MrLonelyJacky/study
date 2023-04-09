package com.jacky.springframework.aop;

import com.jacky.springframework.aop.aspectj.MyAspectJExpressionPointcutAdvisor;
import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyBeanFactory;
import com.jacky.springframework.beans.factory.MyBeanFactoryAware;
import com.jacky.springframework.beans.factory.support.MyDefaultListableBeanFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;
import java.util.Collection;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, MyBeanFactoryAware {
    private MyDefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(MyBeanFactory beanFactory) throws MyBeansException {
        this.beanFactory = (MyDefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //if (isInfrastructureClass(beanClass)) return null;

        Collection<MyAspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(MyAspectJExpressionPointcutAdvisor.class).values();

        for (MyAspectJExpressionPointcutAdvisor advisor : advisors) {
            MyClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue;

            MyAdvisedSupport advisedSupport = new MyAdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            //advisedSupport.setProxyTargetClass(false);

            return new MyProxyFactory(advisedSupport).getProxy();

        }

        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
