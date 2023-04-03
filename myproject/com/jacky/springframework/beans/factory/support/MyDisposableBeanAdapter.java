package com.jacky.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyDisposableBean;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;

import java.lang.reflect.Method;

/**
 *  定义销毁方法适配器(接口和配置)
 *  可能你会想这里怎么有一个适配器的类呢，因为销毁方法有两种甚至多种方式，目前有实现接口 DisposableBean、
 *  配置信息 destroy-method，两种方式。而这两种方式的销毁动作是由 AbstractApplicationContext 在注册虚拟机钩子后看，
 *  虚拟机关闭前执行的操作动作。
 * 那么在销毁执行时不太希望还得关注都销毁那些类型的方法，它的使用上更希望是有一个统一的接口进行销毁，
 * 所以这里就新增了适配类，做统一处理。
 */
public class MyDisposableBeanAdapter implements MyDisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public MyDisposableBeanAdapter(Object bean, String beanName,  MyBeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof MyDisposableBean) {
            ((MyDisposableBean) bean).destroy();
        }
        // 2. 配置信息 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof MyDisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new MyBeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
