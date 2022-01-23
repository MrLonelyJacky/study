package designModel.observModelV3;

/**
 * 主题接口  用于管理一对多的关系，注册删除主题
 * 通知观察者
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
