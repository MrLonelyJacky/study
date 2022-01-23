package dataStruct.chuan;

/**
 * Created by 15151 on 2019/6/17.
 */
public class SimpleModel {
    public static void main(String[] args) {
        char[] chars = new char[]{'g', 'o', 'o', 'd', 'g', 'o', 'o', 'g', 'l', 'e'};
            char[] chars1 = new char[]{'g', 'o', 'o', 'g', 'l', 'e'};
            for (int i = 0; i < chars.length; i++) {
                int m = i;
                for (int j = 0; j < chars1.length; j++) {
                    if (chars[m] == chars1[j]) {
                        m++;
                        if (j == chars1.length - 1) {
                            System.out.println("yes it contains");
                        }
                    } else {
                        break;
                    }
                }
        }

    }
}
