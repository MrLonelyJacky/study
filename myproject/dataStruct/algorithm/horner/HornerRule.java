package dataStruct.algorithm.horner;

/**
 * honer法则求多项式 如4*2^0+4*2^1+4*2^2+4*2^3......4+((4*2+4)*2+4)*2
 * 案例中的a1,a2,a3....an =4   x1,x2...xn=2;
 */
public class HornerRule {
    public static void main(String[] args) {
        int x = 2;
        int an = 4;
        int n = 4;
        System.out.println(caculateNoraml(x, an, n));
        System.out.println(caculateXN(2, 4));
        System.out.println(caculateHornerRule(x, an, n));
    }

    /**
     * horner法则求多项式 复杂度On
     *
     * @param x
     * @param an
     * @param n
     * @return
     */
    private static int caculateHornerRule(int x, int an, int n) {
        int value = an;
        for (int i = 1; i <= n; i++) {
            value = value * x + an;
        }
        return value;
    }

    /**
     * 多项式求和普通方法 复杂度太高
     *
     * @param x
     * @param an
     * @param n
     * @return
     */
    private static int caculateNoraml(int x, int an, int n) {
        int value = an;
        for (int i = 1; i <= n; i++) {
            value = value + an * caculateXN(x, i);
        }
        return value;
    }

    /**
     * 计算x的n次方
     *
     * @param x
     * @param n
     * @return
     */
    private static int caculateXN(int x, int n) {
        int value = 1;
        for (int j = 1; j <= n; j++) {
            value = value * x;
        }
        return value;
    }
}
