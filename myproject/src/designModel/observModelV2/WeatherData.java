package designModel.observModelV2;

public class WeatherData {
    private float temperature;
    private float humidity;
    private float pressure;

    private CurrentConditionsDisplay currentConditionsDisplay;
    private StatisticsConditionDisplay statisticsConditionDisplay;

    /**
     * 这段代码最大的问题是两个板块都是争对实现编程，至少这两个板块都用了update方法
     * 至少可以考虑接口吧    每次有新的板块的时候我们都要修改代码，删除板块也是同样如此
     */
    public void measurementsChanged() {
        float temp = getTemperature();
        float hum = getHumidity();
        float pre = getPressure();
        //目前是两个板块都要更新
        currentConditionsDisplay.update(temp,hum,pre);
        statisticsConditionDisplay.update(temp,hum,pre);
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
