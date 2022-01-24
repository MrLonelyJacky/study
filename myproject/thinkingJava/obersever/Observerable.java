package thinkingJava.obersever;

/**
 * Created by 15151 on 2019/5/14.
 * 被观察者接口
 */
public interface Observerable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();//通知观察者
}
