package concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * @description: 模仿autoInteger
 * @author: jacky
 * @create: 2023-03-16 12:39
 **/
public class UpdateAndGet {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        updateAndGetByFunc(atomicInteger, a -> a * 10);


    }

    private static void updateAndGetByFunc(AtomicInteger atomicInteger, IntUnaryOperator intBinaryOperator) {
        while (true) {
            int prev = atomicInteger.get();
            int result = intBinaryOperator.applyAsInt(prev);
            if (atomicInteger.compareAndSet(prev, result)) {
                break;
            }

        }
    }


}
