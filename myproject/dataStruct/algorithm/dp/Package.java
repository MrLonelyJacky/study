package dataStruct.algorithm.dp;

/**
 * @description:题目描述：
 *
 * 有n件物品,每件物品的重量为w[i], 价值为c[i]。现在需要选出若干件物品放入一个容量为v的背包中,使得在选入背包的物品重量和不超过容量v的前提下,
 * 让背包中物品的价格之和最大,求最大价值。
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
        int[][] dp = new int[weight.length][volume+1];

        for (int j=0;j<=volume;j++){
            if (weight[0]>(j+1)){
                dp[0][j] = 0 ;
            }else {
                dp[0][j] = value[0];
            }
        }

        for (int i=0;i<weight.length;i++){
                dp[i][0] = 0;
        }


        for (int i=1;i<weight.length;i++){
            for (int j=1;j<=volume;j++){
                if (weight[i]>j){
                    //重量大于容量 放不下
                    dp[i][j] = dp[i-1][j];
                }else {
                    int leftVolume = j- weight[i];
                    dp[i][j] =Math.max( value[i] + dp[i-1][leftVolume], dp[i-1][j]);
                }
            }
        }
        return dp[weight.length-1][volume];
    }


    public static void main(String[] args) {

        int[] c = {7, 2, 6, 3, 5};
        //物品的价值
        int[] w = {21, 18, 9, 15, 6};

        //背包的容量
        int v = 14;

        int[] weight = new int[]{2,3,4,7};
        int[] value = new int[]{1,3,5,9};
        int maxValue = getMaxValue(c, w, v);
        System.out.println(maxValue);
    }

}
