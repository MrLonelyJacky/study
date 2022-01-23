package effectiveJava.chapter6;

/**
 * Created by 15151 on 2020/1/17.
 * int enum pattern has many disadvantages int 是编译时常量倘如改变值，客户端未及时编译则有问题
 * 并且int enum 可以在不同类型中进行比较，没有类型安全可言
 * 枚举类是真实的final 类型 不可拓展也不可实例化
 */
public enum Planet {
    /**
     * earth
     * 直到2006 年，即Java 中增加了枚举的两年之后，当时·冥王星P luto 还属于行星。这引
     发出一个问题： 当把一个元素从一个枚举类型中移除时，会发生什么情况呢？答案是： 没有
     引用该元素的任何客户端程序都会继续正常工作。因此，我们的Weight Table 程序只会
     打印出一个少了一行的表格而已。对于引用了被删除元素（如本例中是指Planet.Pl uto)
     的客户端程序又如何呢？如果重新编译客户端程序，就会失败，并在引用被删除行星的那一
     条出现一条错误消息；如果没有重新编译客户端代码，在运行时就会在这一行抛出一个异
     常。这是你能期待的最佳行为了，远比使用int 枚举模式时要好得多。
     */
    EARTH(5.975e+24, 6.378e+6), MARS(6.419e+23, 3.393e+6);
    private final double mass;
    private final double radius;
    private final double surfaceGravity;
    private static final double G = 6.67300E-11;
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass / (radius * radius);
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double getSurfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }
}
