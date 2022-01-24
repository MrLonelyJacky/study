package LeetCode.dynamicPlan;

import java.util.Arrays;

/**
 * @author segi
 * @date 2021/11/24
 * @description 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 递归表达式该是什么？  f(i) = min(f(i-cons[0]),f(i-cons[1]),.......)
 */
public class MoneyLeft {
    int[] memo;


    public int coinChange(int[] coins, int amount) {

        memo = new int[amount + 1];
        Arrays.fill(memo, -666);


        return dp(coins, amount);

    }


    private int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != -666) {
            return memo[amount];
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            result = Math.min(result, dp(coins, amount - coin));
        }
        memo[amount] = result;
        return memo[amount];

    }


    private int dp2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        int result = dp[0];
        for (int i = 1; i < dp.length; i++) {
            for (int j=0; j< coins.length; j++) {
                dp[i] = Math.min(dp[i], dp[i-coins[j]]);
            }
        }

        memo[amount] = result;
        return memo[amount];

    }
}





















