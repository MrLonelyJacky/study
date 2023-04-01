package com.jacky.springframework.test;


import lombok.Data;

@Data
public class UserService {
    private String name;

    private String uId;

    private UserDao userDao;

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

}
