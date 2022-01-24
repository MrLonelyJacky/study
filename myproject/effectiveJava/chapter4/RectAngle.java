package effectiveJava.chapter4;

/**
 * Created by 15151 on 2020/1/8.
 */
public class RectAngle extends Figure {
    final double length;
    final double width;

    public RectAngle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}
