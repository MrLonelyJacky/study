package spring.chapter5;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.text.DateFormat;

public class DateFormatFactoryBean implements FactoryBean<DateFormat>, InitializingBean {
    @Override
    public DateFormat getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
