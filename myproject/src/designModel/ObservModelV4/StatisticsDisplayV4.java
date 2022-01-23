package designModel.ObservModelV4;

import designModel.observModelV3.DisplayElement;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplayV4 implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    final Observable observable;

    public StatisticsDisplayV4(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("satstics display"+temperature+"_"+humidity+"_"+pressure);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherDataV4){
            this.temperature = ((WeatherDataV4) o).getTemperature();
            this.humidity = ((WeatherDataV4) o).getHumidity();
            this.pressure = ((WeatherDataV4) o).getPressure();
            display();
        }
    }
}
