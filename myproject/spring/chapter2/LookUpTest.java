package spring.chapter2;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class LookUpTest {

    private XmlBeanFactory xmlBeanFactory;

    @Before
    public void initXmlBeanFactory(){
        System.out.println("----测试方法开始----");
        xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("./xmlFile/studyOne.xml"));
    }

    @Test
    public void testLookUp(){

        Animal animal = xmlBeanFactory.getBean("animal", Animal.class);
        Animal animal2 = xmlBeanFactory.getBean("animal", Animal.class);
        System.out.println("Animal:singleton,所以animal1==animal2应该为" + (animal == animal2));
        Dog dog1 = animal.getDog();
        Dog dog2 = animal.getDog();
        System.out.println("Dog:prototype,Animal:singleton,未使用lookup-method注入所以dog1==dog2应该为" + (dog1 == dog2));

        //注意:这里是通过createDog()方法获取
        Dog dog3 = animal.createDog();
        Dog dog4 = animal.createDog();
        System.out.println("Dog:prototype,Animal:singleton,使用了lookup-method注入所以dog3==dog4应该为" + (dog3 == dog4));
    }


}
