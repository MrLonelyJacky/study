package dataStruct.algorithm.greedy;

import java.util.*;
import java.util.function.Function;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 * <p>
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SendCandy {

    public static void main(String[] args) {
        int[] ratings = {1, 2, 2};
        int[] ratings2 = {1, 0, 2};
        System.out.println(candy(ratings));
        System.out.println(candy(ratings2));
    }

    /**
     * 基于贪心的算法
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        List<Integer> iList = new ArrayList<>();
        for (int rating : ratings) {
            iList.add(rating);
        }

        Arrays.sort(ratings);
        Map<Integer, Integer> minIndexList = new HashMap<>();

        for (int i = iList.size(); i > 0; i--) {
            //贪心策略，找到最小值 和最小值的坐标
            int asInt = iList.stream().mapToInt(item -> item).min().getAsInt();
            int minIndex = iList.indexOf(asInt);


            if (minIndexList.get(minIndex - 1) != null && minIndexList.get(minIndex + 1) != null) {
                //两边都有发过糖果
                if (asInt == ratings[minIndex - 1] && asInt == ratings[minIndex + 1]) {
                    //两边得分和自己一样，只发一个糖果就行
                    minIndexList.put(minIndex, 1);
                } else if (asInt == ratings[minIndex - 1] && asInt != ratings[minIndex + 1]) {
                    //左边一样，右边不一样,选择右边的糖果数，要比他大一
                    minIndexList.put(minIndex, minIndexList.get(minIndex + 1) + 1);
                } else if (asInt != ratings[minIndex - 1] && asInt == ratings[minIndex + 1]) {
                    //右边不一样，左边一样
                    minIndexList.put(minIndex, minIndexList.get(minIndex - 1) + 1);
                } else {
                    //两边都不一样，选择糖果多的加1
                    int max = Integer.max(minIndexList.get(minIndex - 1), minIndexList.get(minIndex + 1));
                    minIndexList.put(minIndex, max + 1);
                }
            } else if (minIndexList.get(minIndex - 1) != null && minIndexList.get(minIndex + 1) == null) {
                //有一个发糖果，有一个没发，比较是否分数一样，分数一样就是1 ，分数不同就+1
                if (asInt == ratings[minIndex - 1]) {
                    minIndexList.put(minIndex, 1);
                } else {
                    minIndexList.put(minIndex, minIndexList.get(minIndex - 1) + 1);
                }
            } else if (minIndexList.get(minIndex - 1) == null && minIndexList.get(minIndex + 1) != null) {
                if (asInt == ratings[minIndex + 1]) {
                    minIndexList.put(minIndex, 1);
                } else {
                    minIndexList.put(minIndex, minIndexList.get(minIndex + 1) + 1);
                }
            } else {
                minIndexList.put(minIndex, 1);
            }
            //将当前已经处理过的最小值变成最大值
            iList.set(minIndex, Integer.MAX_VALUE);
        }

        return minIndexList.values().stream().mapToInt(item -> item).sum();


    }
}
