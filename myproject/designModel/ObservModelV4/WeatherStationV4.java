package designModel.ObservModelV4;

import designModel.observModelV3.CurrentConditionsDisplay;
import designModel.observModelV3.StatisticsConditionDisplay;
import designModel.observModelV3.WeatherData;

public class WeatherStationV4 {
    public static void main(String[] args) {
        WeatherDataV4 weatherDataV4 = new WeatherDataV4();
        ConditionDisplayV4 conditionDisplayV4 = new ConditionDisplayV4(weatherDataV4);
        StatisticsDisplayV4 statisticsDisplayV4 = new StatisticsDisplayV4(weatherDataV4);
        conditionDisplayV4.update(weatherDataV4,null);
        weatherDataV4.measurementsChanged(11,12,23);
        conditionDisplayV4.update(weatherDataV4,null);
        /*weatherDataV4.measurementsChanged(12,434,34);
        weatherDataV4.measurementsChanged(4,4,34);*/


    }
}
