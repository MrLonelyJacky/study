package concurrent.chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by 15151 on 2020/4/26.
 * 测试自定义的显示锁
 */
public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            //加锁
            lock.lock();
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unLock();
        }
    }

    public static void main(String[] args) {
        BooleanLockTest test = new BooleanLockTest();
        IntStream.range(0, 10).mapToObj(i ->
                new Thread(test::syncMethod)).forEach(Thread::start);
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        Set<String> set = map.keySet();
        map.put("b", "b");
    }
}
