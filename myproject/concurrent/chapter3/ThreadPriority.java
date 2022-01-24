package concurrent.chapter3;

/**
 * Created by 15151 on 2020/4/9.
 * 理论上高优先级的线程会被调用更多次数，但实际上爷也许并不是
 * 所以千万别用优先级去实现业务场景
 */
public class ThreadPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("t1");
            }
        });
        t1.setPriority(3);
        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println("t2");
            }
        });
        t2.setPriority(10);

    }
}
