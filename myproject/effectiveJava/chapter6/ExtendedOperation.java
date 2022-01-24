package effectiveJava.chapter6;

/**
 * Created by 15151 on 2020/2/3.
 * 拓展枚举
 */
public enum ExtendedOperation implements OperationInt {
    /**
     * 幂运算
     */
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    };
    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    public static void main(String[] args) {

    }

    /*private static <T extends Enum & OperationInt> */
}
