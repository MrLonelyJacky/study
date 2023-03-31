package concurrent.cas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 如何保证线程安全呢，BigDecimal
 * @author: jacky
 * @create: 2023-03-20 12:32
 **/
public class AutoRef implements DecimalAccount {

    private AtomicReference<BigDecimal> balance;

    public AutoRef(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while (true) {
            //cas
            BigDecimal prev = balance.get();
            BigDecimal result = prev.subtract(amount);
            if (balance.compareAndSet(prev, result)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        AutoRef autoRef = new AutoRef(new BigDecimal("1000"));
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Thread(() -> {
                autoRef.withdraw(BigDecimal.TEN);
            }));
        }
        list.forEach(Thread::start);
        list.forEach(ts-> {
            try {
                ts.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(autoRef.getBalance());
    }

}
