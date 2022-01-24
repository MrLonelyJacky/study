package thinkingJava.buildModel;

/**
 * Created by 15151 on 2019/5/18.
 */
public class Bike {
    private String tyre;
    private String frame;
    private String gps;

    public String getTyre() {
        return tyre;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "tyre='" + tyre + '\'' +
                ", frame='" + frame + '\'' +
                ", gps='" + gps + '\'' +
                '}';
    }
}
