package com.jacky.springframework.context.event;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyBeanFactory;
import com.jacky.springframework.beans.factory.MyBeanFactoryAware;
import com.jacky.springframework.context.MyApplicationEvent;
import com.jacky.springframework.context.MyApplicationListener;
import com.jacky.springframework.util.ClassUtils;
import org.springframework.beans.BeansException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class MyAbstractApplicationEventMulticaster implements MyApplicationEventMulticaster, MyBeanFactoryAware {
    public final Set<MyApplicationListener<MyApplicationEvent>> applicationListeners = new LinkedHashSet<>();
    private MyBeanFactory beanFactory;
    @Override
    public void addApplicationListener(MyApplicationListener<?> listener) {
        applicationListeners.add((MyApplicationListener<MyApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(MyApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(MyBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected Collection<MyApplicationListener> getApplicationListeners(MyApplicationEvent event) {
        LinkedList<MyApplicationListener> allListeners = new LinkedList<>();
        for (MyApplicationListener<MyApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) allListeners.add(listener);
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     */
    protected boolean supportsEvent(MyApplicationListener<MyApplicationEvent> applicationListener, MyApplicationEvent event) {
        Class<? extends MyApplicationListener> listenerClass = applicationListener.getClass();

        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new MyBeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
