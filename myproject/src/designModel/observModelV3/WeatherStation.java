package designModel.observModelV3;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        weatherData.registerObserver(currentConditionsDisplay);
        StatisticsConditionDisplay statisticsConditionDisplay = new StatisticsConditionDisplay();
        weatherData.registerObserver(statisticsConditionDisplay);
        weatherData.measurementsChanged(11,12,12);
        weatherData.measurementsChanged(21,21,23);

    }
}
