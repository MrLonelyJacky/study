package thinkingJava.Label;

/**
 * Created by 15151 on 2019/1/29.
 * 跳出循环到指定位置
 */
public class label {
    public static void main(String[] args) {
        label1:
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            for (int x = 0; x < 10; x++) {
                System.out.println("x = " + x);
                continue label1;
            }
            System.out.println("haha");
        }
    }
}
