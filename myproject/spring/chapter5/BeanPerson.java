package spring.chapter5;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import spring.chapter2.Dog;
import spring.chapter2.OriginalDog;

public class BeanPerson implements InitializingBean {
    //todo autowire

    //用于lookup-method注入
    public  Dog createDog(){
        return new Dog();
    }

    private StringBuffer stringBuffer;

    public void say(String word) {
        System.out.println("Hello, " + word);
    }

    public BeanPerson() {
        System.out.println("BeanPerson() ");
    }

    public void initMethod(){
        System.out.println("initMethod()....");
    }

    public void destroyMethod(){
        System.out.println("destroyMethod()....");
    }

    /**
     * 该方法会在实例化和属性注入后调用
     * 我们可以应用他 将一些其他属性不好通过属性注入直接注入时，
     * 通过该方法赋值
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.stringBuffer = new StringBuffer();
    }

    public static void main(String[] args) {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("spring/xmlFile/studyOne.xml"));
        BeanPerson originalDog = xmlBeanFactory.getBean("person", BeanPerson.class);

    }
}