package designModel.ObservModelV4;

import java.util.Observable;

public class WeatherDataV4 extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged(float temp, float hum, float pre) {
        this.temperature = temp;
        this.humidity = hum;
        this.pressure = pre;
        setChanged();
        notifyObservers();
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
