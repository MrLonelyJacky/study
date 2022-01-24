package designModel.observModelV3;

/**
 * 观察者接口  当主题通知时，观察者做出相应的变化
 */
public interface Observer {
    void update(float temp, float hum, float pre);
}
