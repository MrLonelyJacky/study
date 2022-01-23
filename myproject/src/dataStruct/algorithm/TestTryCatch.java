package dataStruct.algorithm;

import java.util.Arrays;

/**
 * Created by 15151 on 2019/9/17.
 */
public class TestTryCatch {
   /* public static void main(String[] args) {
        int i = 0;
        try {
            i = 1 / 0;
            i = 3;
        } catch (Exception e) {
            //throw e;
        } finally {
            System.out.println("hhaa");
        }
        System.out.println("还能执行" + i);
        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put("a","b");
        map.put("a","c");
        System.out.println(map.get("a"));
    }*/

    public static void main(String[] args) {
        int array[] = {2, 5, 7, 6, 9, 8, 1};

        Arrays.stream(array).forEach(value -> new Thread(() -> {
            try {
                Thread.sleep(value);
                System.out.print(value + ",");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
    }

}
