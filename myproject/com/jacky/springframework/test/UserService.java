package com.jacky.springframework.test;


import com.jacky.springframework.beans.MyBeansException;
import com.jacky.springframework.beans.factory.*;
import com.jacky.springframework.context.MyApplicationContext;
import com.jacky.springframework.context.MyApplicationContextAware;
import lombok.Data;

@Data
public class UserService implements MyInitializingBean, MyDisposableBean, MyBeanNameAware, MyBeanClassLoaderAware, MyApplicationContextAware, MyBeanFactoryAware {
    private String name;

    private String uId;

    private UserDao userDao;

    private String company;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    public void queryUserInfoByDao() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void setBeanFactory(MyBeanFactory beanFactory) throws MyBeansException {

    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void setApplicationContext(MyApplicationContext applicationContext) throws MyBeansException {

    }
}
