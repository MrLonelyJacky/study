package dataStruct.algorithm;

import java.util.*;

/**
 * Created by 15151 on 2019/9/16.
 * kpm字符串匹配算法  相对于暴力破解算法效率提高不少
 */
public class Kmp {
    /**
     * 短短几行代码但其实晦涩难懂
     * next数组求解过程 next数组从下标为1开始
     * chars[0]存储的是该数组的大小
     */
    public void get_next(char[] chars, int[] next) {
        next[1] = 0;
        int i = 1;
        int j = 0;
        while (i < chars[0]) {
            if (j == 0 || chars[i] == chars[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                //跳转到前缀匹配后的一个位置
                j = next[j];
            }
        }
    }

    /**
     * kmp算法  串中第一位是该串的大小
     *
     * @param T    匹配串T
     * @param P    模式串T
     * @param next
     */
    public boolean kmp(char[] T, char[] P, int[] next) {
        int i = 1;
        int j = 1;
        while (i <= T[0] && j <= P[0]) {
            if (j == 0 || T[i] == T[j]) {
                i++;
                j++;
            } else {
                //跳转到前缀匹配后的一个位置
                j = next[j];
            }
        }
        if (j > P[0]) {
            //匹配
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        char[] ss = "java2novice".toCharArray();

        List<Character> set = new ArrayList<>();

        for(int j = 0;j < ss.length;j++){
            if (set.contains(ss[j])){
                set.clear();
                set.add(ss[j]);
            }else {
                set.add(ss[j]);
            }

        }
        System.out.println(set.toString());


    }
}
