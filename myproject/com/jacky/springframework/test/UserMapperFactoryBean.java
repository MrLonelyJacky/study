package com.jacky.springframework.test;

import com.jacky.springframework.beans.factory.MyFactoryBean;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: jacky
 * @create: 2023-04-05 14:36
 **/
public class UserMapperFactoryBean implements MyFactoryBean<IUserMapper> {

    @Override
    public IUserMapper getObject() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");

        IUserMapper userMapper = new IUserMapper() {
            @Override
            public String queryUserName(String uId) {
                return hashMap.get(uId);
            }
        };
        return userMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return IUserMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
