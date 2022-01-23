package effectiveJava.chapter6;

import java.util.Map;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 15151 on 2020/1/15.
 * 有时候我们需要将行为和枚举常量bind  这时候需要特定于常量的方法实现
 */
public enum Operation {
    /**
     * plus minus times divided operation  Functional programming reform chapter6 Operation
     * lambda expression is not suitable for too long ,one line is the most ideal three lines
     * are the maximum reasonable limit
     */
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDED("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    /**
     * 枚举类内部有一个valueOf的方法会将常量的名字变为自己本身 我们重写toString方法
     * 并提供一个转换的map  注意 枚举构造器中不可以访问静态成员这和初始化的顺序有关
     */
    private static final Map<String, Operation> STRING_TO_ENUM = Stream.of(values())
            .collect(Collectors.toMap(Object::toString, e -> e));

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(STRING_TO_ENUM.get(symbol));
    }

    @Override
    public String toString() {
        return symbol;
    }

    /**
     * 如果能定义成一个接口来拓展就更好了
     * @param x
     * @param y
     * @return
     */
    public abstract double apply(double x, double y);


    public static void main(String[] args) {
        Optional<Operation> operation = fromString("-");
        System.out.println(operation.isPresent() ? operation.get().apply(3, 2) : 0);
    }
}
