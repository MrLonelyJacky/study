package spring.chapter2;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ReplaceTest {
    public static void main(String[] args) {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("spring/xmlFile/studyOne.xml"));
        OriginalDog originalDog = xmlBeanFactory.getBean("originalDog", OriginalDog.class);
        originalDog.sayHello();
    }
}
