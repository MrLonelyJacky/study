package effectiveJava.chapter7;

import java.util.function.DoubleBinaryOperator;

/**
 * Created by 15151 on 2020/1/15.
 */
public enum Operation {
    /**
     * plus minus times divided operation  Functional programming reform chapter6 Operation
     * lambda expression is not suitable for too long ,one line is the most ideal three lines
     * are the maximum reasonable limit
     */
    PLUS("+", Double::sum),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDED("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator operator;

    Operation(String symbol, DoubleBinaryOperator operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    public double apply(double a, double b) {
        return this.operator.applyAsDouble(a, b);
    }

    public static void main(String[] args) {
    }

}
