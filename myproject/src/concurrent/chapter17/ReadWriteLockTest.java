package concurrent.chapter17;

import java.util.Arrays;

public class ReadWriteLockTest {
    private final static String text = "Thisistheexampleforreadwritelock";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);
        for (int i = 0; i < 2; i++) {
            //开两个线程写
            new Thread(() -> {
                for (int index = 0; index < text.length(); index++) {
                    char c = text.charAt(index);
                    try {
                        shareData.write(c);
                        System.out.println(Thread.currentThread() + "write" + c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            //开启十个线程读取
            new Thread(() -> {
                try {
                    char[] read = shareData.read();
                    System.out.println(Thread.currentThread() + "read" + Arrays.toString(read));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
