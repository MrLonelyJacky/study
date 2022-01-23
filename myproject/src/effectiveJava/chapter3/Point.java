package effectiveJava.chapter3;

import java.awt.*;

/**
 * Created by 15151 on 2019/12/19.
 * equals 传递性违反示例
 * 此外，这种方法还可能导致无限递归问题： 假设Point 有两个子类，如ColorPoint
 * 和SmellPoint ，它们各自都带有这种equals 方法。那么对myColorPoint.equals
 * (mySmellP o int ）的调用将会抛出StackOverfl owError 异常。
 * 里氏替换原则（ Liskov substitution principle ）认为， 一个类型的任何重要属性也将适用于它
 * 的子类型，因此为该类型编写的任何方法，在它的子类型上也应该同样运行得很好
 */
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 该equals 违反传递性
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }

    /**
     * 子类复写equals
     */
    public static class ColorPoint extends Point {
        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            if (!(o instanceof ColorPoint)) {
                return o.equals(this);
            }
            return super.equals(o) && ((ColorPoint) o).color == color;
        }
    }

    public static class NumberPoint {
        private final Point point;
        private final Color color;

        public NumberPoint(int x, int y, Color color) {
            point = new Point(x, y);
            this.color = color;
        }

        public Point asPoint() {
            return this.point;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof NumberPoint)) {
                return false;
            }
            NumberPoint numberPoint = (NumberPoint) o;
            return numberPoint.point.equals(point) && numberPoint.color.equals(color);
        }
    }

    /**
     * p1 p2 等  p2 p3等 p1 p3不等
     *
     * @param args
     */
    public static void main(String[] args) {
        ColorPoint p1 = new Point.ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        NumberPoint numberPoint = new NumberPoint(1, 2, Color.RED);
        System.out.println(numberPoint.equals(p2));
        System.out.println(p2.equals(numberPoint));
    }
}
