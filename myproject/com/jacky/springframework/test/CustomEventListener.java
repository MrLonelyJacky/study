package com.jacky.springframework.test;

import com.jacky.springframework.context.MyApplicationListener;

import java.util.Date;

public class CustomEventListener implements MyApplicationListener<CustomerEvent> {

    @Override
    public void onApplicationEvent(CustomerEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
