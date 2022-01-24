package thinkingJava.buildModel;

/**
 * Created by 15151 on 2019/5/18.
 */
public interface BikeBuilder {
    void buildTyres();
    void buildFrame();
    void buildGps();
    Bike getBike();
}
