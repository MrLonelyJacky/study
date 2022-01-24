package designModel.obserModel;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements Observer,DisplayElement {

    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        //默认构造函数注册上去
        observable.addObserver(this);
    }



    @Override
    public void display() {

    }

    /**
     * 把被观察者传输过去，方便拉去主题的数据
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData){
            this.temperature = ((WeatherData) o).getTemperature();
            this.humidity = ((WeatherData) o).getHumidity();
            display();
        }
    }
}
