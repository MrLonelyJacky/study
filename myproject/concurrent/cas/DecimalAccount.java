package concurrent.cas;

import java.math.BigDecimal;

/**
 * @description:
 * @author: jacky
 * @create: 2023-03-20 12:33
 **/
public interface DecimalAccount {
    //获取余额
    BigDecimal getBalance();
    //取款
    void withdraw(BigDecimal amount);
}
