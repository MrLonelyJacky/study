package spring.chapter6;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.support.DefaultConversionService;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MessageFormatTest {

    private ApplicationContext context;

    @Before
    public void initXmlBeanFactory(){
        String[] configs = {"spring/xmlFile/studyOne.xml"};
        this.context = new ClassPathXmlApplicationContext(configs);
    }

    @Test
    public void testMessage(){
        //1.信息格式化
        String pattern1 = "{0}，您好！{1}工商银行存入{2}元。";
        String pattern2 = "At{1,time,short} on{1,date,long} {0} paid {2,number,currency}.";
        //2.用于动态替换占位符的参数
        Object[] params = {"John",new GregorianCalendar().getTime(),1.0E3};
        //3使用默认本地化对象格式化信息
        String msg1 = MessageFormat.format(pattern1, params);
        System.out.println(msg1);
        //4使用指定的语言格式化
        MessageFormat messageFormat = new MessageFormat(pattern2, Locale.US);
        String msg2 = messageFormat.format(params);
        System.out.println(msg2);
    }


    @Test
    public void testResouceBoundle(){

        //直接通过容器访问国际化信息
        Object[] params = {"John",new GregorianCalendar().getTime(),1.0E3};
        // ch
        String can = context.getMessage("application-name", params, Locale.CHINESE);
        System.out.println(can);
        String ca = context.getMessage("author", params, Locale.CHINESE);
        System.out.println(ca);
        String cv = context.getMessage("version", params, Locale.CHINESE);
        System.out.println(cv);
        // en
        String ean = context.getMessage("application-name", params, Locale.ENGLISH);
        System.out.println(ean);
        String ea = context.getMessage("author", params, Locale.ENGLISH);
        System.out.println(ea);
        String ev = context.getMessage("version", params, Locale.ENGLISH);
        System.out.println(ev);

    }

    @Test
    public void testListener(){
        TestEvent testEvent =new TestEvent("hello","msg");
        context.publishEvent(testEvent);

    }


    @Test
    public void  testStringToPhoneNumberConvert(){
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new String2DateConvert());
    }











}
