package concurrent.chapter4;

import java.util.HashMap;

/**
 * Created by 15151 on 2020/4/22.
 *
 */
public class HashMapDeadLock {
    //TODO 理解hashMap循环死锁
    private final HashMap<String, String> map = new HashMap<>();

    public void add(String key, String value) {
        this.map.put(key, value);
    }

    public static void main(String[] args) {
        final HashMapDeadLock mapDeadLock = new HashMapDeadLock();
        for (int x = 0; x < 2; x++) {
            new Thread(() -> {
                for (int i = 1; i < Integer.MAX_VALUE; i++) {
                    mapDeadLock.add(String.valueOf(i),String.valueOf(i));
                }
            });
        }
    }
}
