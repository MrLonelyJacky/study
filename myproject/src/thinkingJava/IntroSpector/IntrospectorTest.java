package thinkingJava.IntroSpector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by 15151 on 2019/6/19.
 */
public class IntrospectorTest {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(BeanA.class);
        //通过Introspector类获得Bean对象的 BeanInfo， 然后通过BeanInfo 来获取属性的描述器（ PropertyDescriptor ）
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : propertyDescriptors) {
            System.out.println(pd.getName());
        }
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("title", BeanA.class);
    }
}
