package effectiveJava.chapter9;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/3/7.
 * as is known to all,float and double can lose accuracy
 * especially the number 0.1 so we recommend the BigDecimal instead of double
 * another method is to use long or int but u need handle with decimal point
 */
public class BigDecimalBetter {
    public static void main(String[] args) {
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
        System.out.printf("tiem"+itemsBought+":money left"+funds);
    }
}
