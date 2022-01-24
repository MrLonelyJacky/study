package designModel.observModelV3;

public class StatisticsConditionDisplay implements Observer,DisplayElement{
    public void update(float temp, float hum, float pre) {
        System.out.println("static update temp"+temp+",hum"+hum+"pre"+pre);
    }

    @Override
    public void display() {

    }
}
