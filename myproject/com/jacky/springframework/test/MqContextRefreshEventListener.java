package com.jacky.springframework.test;

import com.jacky.springframework.context.MyApplicationListener;
import com.jacky.springframework.context.event.MyContextRefreshedEvent;

public class MqContextRefreshEventListener implements MyApplicationListener<MyContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(MyContextRefreshedEvent event) {
        System.out.println("容器刷新了");
        System.out.println("开始监听mq。。。。。");
    }
}
