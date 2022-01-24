package concurrent.practise.chapter7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 简单的取消案例，但是run方法中有可中断的方法，则线程会停掉
 */
public class PrimeGenerate implements Runnable {
    private final List<BigInteger> primes = new ArrayList<>();
    /**
     * 取消开关
     */
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
            //如果这里抛非受检异常 调用aSecondOfPrimes者压根捕获不了该异常，因为在两个不同的线程内
            int i = 1 / 0;
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }

    public static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerate generate = new PrimeGenerate();
        new Thread(generate).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } finally {
            generate.cancel();
        }
        return generate.get();
    }

    public static void main(String[] args) {
        try {
            List<BigInteger> bigIntegers = aSecondOfPrimes();
            for (BigInteger bigInteger:bigIntegers){
                System.out.println(bigInteger);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
