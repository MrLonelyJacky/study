package algorithm.dp;

/**
 * @description: dp求斐波那契数列
 * 1、确定dp数组的含义  dp[i]代表什么  这里表示第i个数列的值
 * 2、递推公式 dp[i] = dp[i-1] + dp [i-2];
 * 3、初始化和边界问题  dp[0] =1  dp[1]=1
 * 4、遍历顺序 从前向后
 * 5、打印
 * @author: jacky
 * @create: 2023-01-07 16:59
 **/
public class FibonacciSeries {
    public static void main(String[] args) {
        int[] dp = new int[10];
        dp[0] =1 ; dp[1]=1;
        for (int i =2;i<dp.length;i++){
            dp[i] = dp[i-1] + dp [i-2];
            System.out.println(dp[i]);
        }
    }
}
