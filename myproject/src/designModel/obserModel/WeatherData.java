package designModel.obserModel;

import java.util.Observable;

/**
 * 观察者模式 分push 和 pull两种模型  主题类，用来通知观察者
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged() {
        setChanged();
        notifyObservers();//我们并没有传送数据  表示pull模型
        //notifyObservers(temperature);  push
    }

    public void setMeasurement(float temperature, float humidity, float pressure) {
        this.temperature=temperature;
        this.humidity = humidity;
        this.pressure =pressure;
        measurementsChanged();
    }

    //for pull model
    public float getTemperature() {
        return temperature;
    }
    //for pull model
    public float getHumidity() {
        return humidity;
    }
    //for pull model
    public float getPressure() {
        return pressure;
    }
}
