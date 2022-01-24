package thinkingJava.obersever;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/5/14.
 * 被观察者微信公众号
 */
public class WechatServer implements Observerable {
    private List<Observer> observerList;//观察者集合
    private String message;

    public WechatServer() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!this.observerList.isEmpty()) {
            this.observerList.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : this.observerList
                ) {
            observer.update(message);
        }
    }

    public void setInformation(String message) {
        this.message = message;
        System.out.println("发布消息：" + message);
        notifyObserver();
    }
}
