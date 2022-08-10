package spring.chapter6;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

    public MyClassPathXmlApplicationContext(String... configLocation) throws BeansException {
        super(configLocation);
    }

    @Override
    protected void initPropertySources() {
        getEnvironment().getSystemProperties().put("applicationName", "bobo");
        getEnvironment().setRequiredProperties("VAR");
    }

    public static void main(String[] args) {

        String[] configs = {"spring/xmlFile/studyOne.xml"};
        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext = new MyClassPathXmlApplicationContext(configs);
        Object animal = myClassPathXmlApplicationContext.getBean("animal");
    }
}
