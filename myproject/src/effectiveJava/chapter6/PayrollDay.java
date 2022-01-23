package effectiveJava.chapter6;

import java.util.EnumSet;

/**
 * Created by 15151 on 2020/1/19.
 */
public enum PayrollDay {
    /**
     *
     */
    MONDAY(), TUESDAY(), WEDNESDAY(), THURSDAY(), FRIDAY(), SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
    private static final int MIN_PRE_SHIFT = 8 * 60;
    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
        this.payType = PayType.WEEKDAY;
    }

    /**
     * 计算工资 但是可维护性并不好 如果加了一个特殊的常量比如假期
     * 这时switch如果未能写就容易出错
     *
     * @param minutesWorked
     * @param payRate
     * @return
     */
    /*public int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;
        int overtimePay;
        switch (this) {
            case SUNDAY:
            case SATURDAY:
                overtimePay = basePay / 2;
                break;
            default:
                overtimePay = minutesWorked <= MIN_PRE_SHIFT ? 0 : (minutesWorked - MIN_PRE_SHIFT) * payRate / 2;
        }
        return basePay + overtimePay;
    }*/
    public int pay(int minutesWorked, int payRate) {
        int basePay = minutesWorked * payRate;
        return basePay + this.payType.overTimePay(minutesWorked, payRate);
    }

    /**
     * 策略枚举 虽然代码没有那么简洁 但是可维护性和安全性更高
     */
    private enum PayType {
        /**
         *
         */
        WEEKDAY {
            @Override
            int overTimePay(int minutesWorked, int payRate) {
                return minutesWorked <= MIN_PRE_SHIFT ? 0 : (minutesWorked - MIN_PRE_SHIFT) * payRate / 2;
            }
        }, WEEKEND {
            @Override
            int overTimePay(int minutesWorked, int payRate) {
                int basePay = minutesWorked * payRate;
                return basePay / 2;
            }
        };

        abstract int overTimePay(int minutesWorked, int payRate);
    }

    public static void main(String[] args) {

    }
}
