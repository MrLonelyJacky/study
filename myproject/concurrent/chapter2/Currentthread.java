package concurrent.chapter2;

/**
 * Created by vinci on 2020/4/9.
 * the method Thread.currentThread is very important
 */
public class Currentthread {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();
    }
}
