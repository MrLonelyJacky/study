package com.jacky.springframework.beans.factory.config;

import com.jacky.springframework.beans.factory.PropertyValues;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @description: bean定义信息
 * @author: jacky
 * @create: 2023-03-31 14:16
 **/
@Data
public class MyBeanDefinition {
    public static final String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    public static final String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private Class beanClass;
    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public MyBeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public MyBeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    //在xml注册Bean定义时，通过scope字段来判断是单例还是原型
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
