package concurrent.practise.chapter7;

import dataStruct.stackAndQueue.SqQueue;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 为了防止线程中断，采用中断表哦哦及更为合适
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {
            while (!cancelled) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        cancelled = true;
    }

    void consumePrimes() {
        BlockingQueue<BigInteger> primesQueue = new ArrayBlockingQueue<>(100);
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(primesQueue);
        primeProducer.start();
    }
}
