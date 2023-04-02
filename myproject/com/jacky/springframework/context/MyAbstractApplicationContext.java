package com.jacky.springframework.context;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyConfigurableListableBeanFactory;
import com.jacky.springframework.beans.factory.config.MyBeanFactoryPostProcessor;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;
import com.jacky.springframework.core.io.DefaultResourceLoader;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

import java.util.Map;

/**
 * 应用上下文抽象类实现 实际上是个模板
 */
public abstract class MyAbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext{
    @Override
    public void refresh() throws MyBeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        // 2. 获取 BeanFactory
        MyConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);
        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected void registerBeanPostProcessors(MyConfigurableListableBeanFactory beanFactory) {
        Map<String, MyBeanPostProcessor> beansOfType = beanFactory.getBeansOfType(MyBeanPostProcessor.class);
        beansOfType.values().forEach(beanFactory::addBeanPostProcessor);
    }

    protected  void invokeBeanFactoryPostProcessors(MyConfigurableListableBeanFactory beanFactory){
        Map<String, MyBeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(MyBeanFactoryPostProcessor.class);
        beansOfType.values().forEach(item->item.postProcessBeanFactory(beanFactory));
    }

    protected abstract void refreshBeanFactory() throws MyBeansException;

    protected abstract MyConfigurableListableBeanFactory getBeanFactory();
}
