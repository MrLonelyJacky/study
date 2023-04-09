package com.jacky.springframework.test;

public class UserMapperImp implements IUserMapper{
    @Override
    public String queryUserName(String uId) {
        return "jacky";
    }
}
