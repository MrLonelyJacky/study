package thinkingJava.obersever;

/**
 * Created by 15151 on 2019/5/14.
 * 观察者，具体用户
 */
public class User implements Observer {
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + "收到消息：" + message);
    }

    public static void main(String[] args) {
        WechatServer wechatServer = new WechatServer();
        User user = new User("a");
        User user2 = new User("b");
        User user3 = new User("c");
        User user4 = new User("d");
        wechatServer.addObserver(user);
        wechatServer.addObserver(user2);
        wechatServer.addObserver(user3);
        wechatServer.addObserver(user4);
        wechatServer.setInformation("tonight will rain");
        wechatServer.removeObserver(user4);
        System.out.println(user4.name+"退出订阅");
        wechatServer.setInformation("tomorrow it wont rain");
    }
}
