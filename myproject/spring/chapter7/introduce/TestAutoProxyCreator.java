package spring.chapter7.introduce;

import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

public class TestAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        AutoConfig annotation = AnnotationUtils.findAnnotation(beanClass, AutoConfig.class);
        if (annotation == null) {
            //直接返回 不做代理
            return null;
        }
        String version = annotation.version();
        findConfigByVersion(version);
        JdkAutoConfigProxy jdkAutoConfigProxy = new JdkAutoConfigProxy();
        return jdkAutoConfigProxy.getProxy();
    }


    //todo 实现他
    private void findConfigByVersion(String version) {

    }

}
