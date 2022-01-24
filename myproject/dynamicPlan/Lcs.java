package LeetCode.dynamicPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * the lcs is the longest common sequence
 * for example    m: a,b,c,d    n：f,b,d,g   the lcs is b,d
 * the most important thing is to find the recursive expression
 * in the question  when m[i]=n[j] lcs = lcs(m[i-1],n[j-1])+1
 * when m[i]!=n[j] lcs = max(lcs(m[i-1],n[j]),lcs(m[i],n[j-1])
 *      a b c d
 *    0 0 0 0 0
 *  f 0 0 0 0 0
 *  b 0 0 1 1 1
 *  d 0 0 1 1 2
 *  g 0 0 1 1 2
 */
public class Lcs {
    public static void main(String[] args) {
        System.out.println(0.3==(0.1+0.2));
        List<List<String>> commonList = new ArrayList<>();
        List<String> mList = Arrays.asList("a", "b", "c","d");
        List<String> nList = Arrays.asList("f", "b", "d");
        int[][] common = new int[mList.size() + 1][nList.size() + 1];
        //初始化数组
        for (int i = 0; i < common[0].length; i++) {
            common[0][i] = 0;
        }

        for (int i = 0; i < common.length; i++) {
            common[i][0] = 0;
        }
        /**
         * 写下思路  相等则等于左上角+1
         * 不等则等于斜上角两个数的最大值
         */
        for (int i = 0; i < mList.size(); i++) {
            for (int j = 0; j < nList.size(); j++) {
                if (mList.get(i).equals(nList.get(j))) {
                    //common从i+1和j+1开始是因为 保留第一位为0
                    common[i + 1][j + 1] = common[i][j] + 1;
                } else {
                    common[i + 1][j + 1] = Math.max(common[i + 1][j], common[i][j + 1]);
                }
            }
        }

        System.out.println("....");
    }
}
