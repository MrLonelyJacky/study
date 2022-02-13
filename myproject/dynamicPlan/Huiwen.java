package dynamicPlan;

import java.util.Objects;

public class Huiwen {

    public static void main(String[] args) {
        System.out.println(isHuiWen("abab"));
        System.out.println(isHuiWen("aaa"));
        System.out.println(isHuiWen("aba"));
        System.out.println(isHuiWen("abba"));
        System.out.println(isHuiWen("abbc"));
    }

    private int a=0;
    private int b;

    public void solve(String s, int i, int j) {
        int length = s.length();



        if (s.charAt(i) == s.charAt(j)) {
            solve(s,i+1,j-1);
        }else {
            a=i;
            b=j;
            solve(s,i+1,j);
            solve(s,i,j-1);
        }


    }


    public static boolean isHuiWen(String s) {
        //todo 验证s
        int length = s.length();
        boolean flag = true;
        for (int i = 0; i < length / 2; i++) {
            if (s.charAt(i) != s.charAt(length - i - 1)) {
                flag = false;
                break;
            }
        }
        return flag;

    }


    /**
     * 动态规划解决最长回文子串，状态转移方程 dp[i][j] =  (s[i]==s[j])&&dp[i+1]==dp[j-1]
     * 边界条件 以及baseCase
     */
    public static void dpSolve(String s){
        int length= s.length();
        //定义dp数组表示s[i....j]是否回文串
        boolean dp[][] = new boolean[length][length];
        //base case
        for (int i=0;i<length;i++){
            dp[i][i]=true;
        }
        //
        for (int i=1;i<length;i++){
            for (int j=0;j<length;j++){
                //if (j-i<3)
            }
        }
    }

}
