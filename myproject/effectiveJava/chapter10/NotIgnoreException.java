package effectiveJava.chapter10;

import java.util.Arrays;

/**
 * Created by 15151 on 2020/3/20.
 * do not ignore exception like use a empty catch
 * sometimes u can ignore like close FileInputStream
 * if u choose to ignore it please explain why and
 * the exception variable should be named as ignore
 */
public class NotIgnoreException {
    public static void main(String[] args) {
        int array[] = {3, 5, 7, 6, 9, 8, 1};

        Arrays.stream(array).forEach(value -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(value);
                        System.out.print(value + ",");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }
}
