package thinkingJava.buildModel;

/**
 * Created by 15151 on 2019/5/18.
 */
public class MoBikeBuilder implements BikeBuilder{
    // 拥有单车对象
    Bike bike = new Bike();
    @Override
    public void buildTyres() {
        bike.setTyre("mobike tyres");
    }

    @Override
    public void buildFrame() {
        bike.setFrame("mobike frames");
    }

    @Override
    public void buildGps() {
        bike.setGps("mobike gps");
    }

    @Override
    public Bike getBike() {
        return bike;
    }

    public static void main(String[] args) {

    }
}
