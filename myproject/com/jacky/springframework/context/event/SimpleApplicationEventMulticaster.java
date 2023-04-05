package com.jacky.springframework.context.event;


import com.jacky.springframework.beans.factory.MyBeanFactory;
import com.jacky.springframework.context.MyApplicationEvent;
import com.jacky.springframework.context.MyApplicationListener;

import java.util.Collection;

/**
 * Simple implementation of the
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class SimpleApplicationEventMulticaster extends MyAbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(MyBeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void multicastEvent(MyApplicationEvent event) {
        Collection<MyApplicationListener> applicationListeners = getApplicationListeners(event);
        applicationListeners.forEach(item -> item.onApplicationEvent(event));
    }
}
