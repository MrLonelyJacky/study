package algorithm.dp;

/**
 * @description:题目描述：
 *
 * 有n件物品，每件物品的重量为w[i], 价值为c[i]。现在需要选出若干件物品放入一个容量为v的背包中，使得在选入背包的物品重量和不超过容量v的前提下，
 * 让背包中物品的价格之和最大，求最大价值。
 *
 * 示例：
 *
 * 登录后复制
 * 输入：物品重量：3 5 1 2 2  物品价值：4 5 2 1 3
 *
 * 输出：10
 * @author: jacky
 * @create: 2023-03-03 17:48
 **/
public class Package {

    /**
     *
     * @param weight
     * @param value
     * @return
     */
    public static  int getMaxValue(int[] weight,int[] value,int volume){
        int[][] dp = new int[weight.length][volume];

        for (int i=0;i<volume;i++){
            if (weight[0]<(i+1)){
                dp[0][i] = 0 ;
            }else {
                dp[0][i] = value[0];
            }
        }

        for (int j=0;j<weight.length;j++){
            if (weight[j]<=1) {
                dp[j][0] = weight[j];
            }else {
                dp[j][0] = 0;
            }
        }


        for (int i=1;i<weight.length;i++){
            for (int j=1;j<volume;j++){
                if (weight[i]>(i+1)){
                    dp[i][j] = dp[i][j-1];
                }else {
                    int leftVolume = i + 1 - weight[i];
                    dp[i][j] =Math.max( weight[i] + dp[leftVolume][j], dp[i][j-1]);
                }
            }
        }
        return dp[volume-1][weight.length-1];
    }


    public static void main(String[] args) {

        int[] c = {7, 2, 6, 3, 5};
        //物品的价值
        int[] w = {21, 18, 9, 15, 6};

        //背包的容量
        int v = 14;

        int[] weight = new int[]{3 ,5, 1, 2, 2};
        int[] value = new int[]{4 ,5 ,2 ,1, 3};
        int maxValue = getMaxValue(c, w, v);
        System.out.println(maxValue);
    }

}
