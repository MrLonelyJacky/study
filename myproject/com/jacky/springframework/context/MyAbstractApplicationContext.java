package com.jacky.springframework.context;

import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.MyConfigurableListableBeanFactory;
import com.jacky.springframework.beans.factory.config.MyBeanFactoryPostProcessor;
import com.jacky.springframework.beans.factory.config.MyBeanPostProcessor;
import com.jacky.springframework.context.event.MyApplicationEventMulticaster;
import com.jacky.springframework.context.event.MyContextClosedEvent;
import com.jacky.springframework.context.event.MyContextRefreshedEvent;
import com.jacky.springframework.context.event.SimpleApplicationEventMulticaster;
import com.jacky.springframework.context.support.ApplicationContextAwareProcessor;
import com.jacky.springframework.core.io.DefaultResourceLoader;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

import java.util.Collection;
import java.util.Map;

/**
 * 应用上下文抽象类实现 实际上是个模板
 */
public abstract class MyAbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext{
    private MyApplicationEventMulticaster applicationEventMulticaster;
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    @Override
    public void refresh() throws MyBeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        // 2. 获取 BeanFactory
        MyConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);
        // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        registerListeners();
        // 8. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
        // 9. 发布容器刷新完成事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        MyConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<MyApplicationListener> applicationListeners = getBeansOfType(MyApplicationListener.class).values();
        for (MyApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new MyContextRefreshedEvent(this));
    }


    @Override
    public void publishEvent(MyApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws MyBeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws MyBeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws MyBeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new MyContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }
}
