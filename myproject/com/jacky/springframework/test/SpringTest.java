package com.jacky.springframework.test;

import com.jacky.springframework.aop.MyAdvisedSupport;
import com.jacky.springframework.aop.TargetSource;
import com.jacky.springframework.aop.framework.MyJdkDynamicAopProxy;
import com.jacky.springframework.aop.aspectj.MyAspectJExpressionPointcut;
import com.jacky.springframework.beans.factory.PropertyValue;
import com.jacky.springframework.beans.factory.PropertyValues;
import com.jacky.springframework.beans.factory.config.MyBeanDefinition;
import com.jacky.springframework.beans.factory.config.MyBeanReference;
import com.jacky.springframework.beans.factory.support.MyDefaultListableBeanFactory;
import com.jacky.springframework.beans.factory.support.MyXmlBeanDefinitionReader;
import com.jacky.springframework.context.MyClassPathXmlApplicationContext;
import org.junit.Test;

public class SpringTest {
    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        MyDefaultListableBeanFactory beanFactory = new MyDefaultListableBeanFactory();
        MyBeanDefinition myBeanDefinition = new MyBeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",myBeanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_BeanFactory2(){
        // 1.初始化 BeanFactory
        MyDefaultListableBeanFactory beanFactory = new MyDefaultListableBeanFactory();
        MyBeanDefinition myBeanDefinition = new MyBeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", myBeanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService","jiaqi");
        userService.queryUserInfo();
    }

    @Test
    public void test_BeanFactory3(){
        // 1.初始化 BeanFactory
        MyDefaultListableBeanFactory beanFactory = new MyDefaultListableBeanFactory();
        MyBeanDefinition useDaoDefinition = new MyBeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", useDaoDefinition);
        MyBeanDefinition myBeanDefinition = new MyBeanDefinition(UserService.class);
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue idValue = new PropertyValue("uId","10001");
        propertyValues.addPropertyValue(idValue);
        PropertyValue dao = new PropertyValue("userDao",new MyBeanReference("userDao"));
        propertyValues.addPropertyValue(dao);
        myBeanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBeanDefinition("userService", myBeanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService","jiaqi");
        userService.queryUserInfo();
        userService.queryUserInfoByDao();
    }

    @Test
    public void test_BeanFactory4(){
        // 1.初始化 BeanFactory
        MyDefaultListableBeanFactory beanFactory = new MyDefaultListableBeanFactory();
        MyXmlBeanDefinitionReader xmlBeanDefinitionReader = new MyXmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("E:\\project\\study\\myproject\\com\\jacky\\springframework\\spring.xml");
        UserService userService = (UserService) beanFactory.getBean("userService","jiaqi");
        userService.queryUserInfo();
        userService.queryUserInfoByDao();
    }

    @Test
    public void test_BeanFactory5(){
        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext = new MyClassPathXmlApplicationContext("E:\\project\\study\\myproject\\com\\jacky\\springframework\\spring.xml");
        UserService userService = (UserService) myClassPathXmlApplicationContext.getBean("userService","jiaqi");
        userService.queryUserInfo();
        userService.queryUserInfoByDao();
        System.out.println(userService);
    }

    @Test
    public void test_BeanFactory6(){
        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext = new MyClassPathXmlApplicationContext("E:\\projectFile\\study\\myproject\\com\\jacky\\springframework\\spring.xml");
        myClassPathXmlApplicationContext.registerShutdownHook();
        UserService userService = (UserService) myClassPathXmlApplicationContext.getBean("userService","jiaqi");
        userService.queryUserInfo();
        userService.queryUserInfoByDao();
        System.out.println(userService);
    }

    @Test
    public void test_BeanFactory7(){
        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext = new MyClassPathXmlApplicationContext("E:\\project\\study\\myproject\\com\\jacky\\springframework\\spring.xml");
        myClassPathXmlApplicationContext.registerShutdownHook();
        UserService userService = (UserService) myClassPathXmlApplicationContext.getBean("userService","jiaqi");
        userService.queryUserInfo();
        userService.queryUserInfoByDao();
        userService.queryUserMapperInfo();
        System.out.println(userService);
    }

    @Test
    public void test_BeanFactory8(){
        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext = new MyClassPathXmlApplicationContext("E:\\projectFile\\study\\myproject\\com\\jacky\\springframework\\listener.xml");
        myClassPathXmlApplicationContext.registerShutdownHook();
        myClassPathXmlApplicationContext.publishEvent(new CustomerEvent(myClassPathXmlApplicationContext,1L,"i love my mother and father"));

    }


    @Test
    public void test_dynamic() {
        //target object
        IUserMapper userMapper = new UserMapperImp();

        //组装代理信息
        MyAdvisedSupport myAdvisedSupport = new MyAdvisedSupport();
        myAdvisedSupport.setTargetSource(new TargetSource(userMapper));
        myAdvisedSupport.setMethodInterceptor(new UserMapperInterceptor());
        myAdvisedSupport.setMethodMatcher(new MyAspectJExpressionPointcut("execution(* com.jacky.springframework.test.IUserMapper.*(..))"));

        IUserMapper proxy = (IUserMapper) new MyJdkDynamicAopProxy(myAdvisedSupport).getProxy();
        proxy.queryUserName("");
    }
}
