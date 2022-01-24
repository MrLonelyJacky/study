package designModel.observModelV3;

public class CurrentConditionsDisplay implements Observer,DisplayElement{
    public void update(float temp, float hum, float pre) {
        System.out.println("currentConditon update"+temp+"-"+hum+"_"+pre);
    }

    @Override
    public void display() {

    }
}
