package effectiveJava.chapter4;

/**
 * Created by 15151 on 2020/1/8.
 */
public class Circle extends Figure {
    final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}
