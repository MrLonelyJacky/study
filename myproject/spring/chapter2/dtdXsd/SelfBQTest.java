package spring.chapter2.dtdXsd;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import spring.chapter2.OriginalDog;

public class SelfBQTest {
    public static void main(String[] args) {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("spring/chapter2/dtdXsd/selfBeanDefinition.xml"));
        Note originalDog = xmlBeanFactory.getBean("note", Note.class);
        originalDog.getEmail();
    }
}
