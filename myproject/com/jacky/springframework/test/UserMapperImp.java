package com.jacky.springframework.test;

public class UserMapperImp implements IUserMapper{
    @Override
    public String queryUserName(String uId) {
        System.out.println("查询了"+uId);
        return "jacky";
    }
}
