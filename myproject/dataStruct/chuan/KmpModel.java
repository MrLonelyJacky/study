package dataStruct.chuan;

/**
 * Created by 15151 on 2019/6/18.
 * kmp匹配模式
 */
public class KmpModel {
    public static void main(String[] args) {

    }

    public static int Index_KMP(String s, String t, int pos) {
        int i = pos;//主串匹配下标值
        int j = 1;//字串总是从下标1开始
        int[] next = new int[255];
        return 1;
    }

    //通过计算返回子串t的next数组
    public static void getNext(char[] t, int[] next) {
        //abcab前后缀分离
        for (int i = 1; i < t.length; i++) {
            if (i == 1) {
                next[1] = 0;
            }
            if (i == 2) {
                //i--->
            }
        }

    }
}
