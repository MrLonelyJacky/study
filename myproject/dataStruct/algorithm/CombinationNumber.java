package dataStruct.algorithm;

import dataStruct.list.CustomList;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/8/30.
 * 求数的组合算法
 */
public class CombinationNumber {

    public static void main(String[] args) {
        List<List<Integer>> customLists = new ArrayList<>();

        //getCombineNum(0, 6, customLists, 1, 2, 3, 4, 5);
        /*int[] array = {1, 2, 3, 4, 5};
        getCombineNum(6, customLists, array);
        for (List<Integer> list : customLists) {
            for (Integer i : list) {
                System.out.println(i);
            }
        }*/
        Integer i = 1;
        changeInteger(i);
        System.out.println(i);
    }

    public static void changeInteger(Integer integer) {
        integer = integer + 1;
    }

    private static void getCombineNum(int combineNum, List<List<Integer>> customLists, int[] array) {
        for (int i = 0; i < array.length; i++) {
            List<Integer> combineNumbers = new CustomList<>();
            if (addCombineNum(combineNum, array, i, combineNumbers, 0)) {
                customLists.add(combineNumbers);
            }
        }

    }

    /**
     * 递归追寻下一级 判断是否满足组合要求
     *
     * @param combineNum
     * @param array          搜寻数组
     * @param index          下标
     * @param combineNumbers 组合数list
     * @param count          递归次数控制
     * @return
     */
    private static boolean addCombineNum(int combineNum, int[] array, int index, List<Integer> combineNumbers, int count) {
        if (count > 100) {
            //递归一百次就让它结束把
            return false;
        }
        int dValue = combineNum - array[index];
        if (dValue < 0) {
            return false;
        } else if (dValue == 0) {
            combineNumbers.add(array[index]);
            return true;
        } else {
            combineNumbers.add(array[index]);
            return addCombineNum(dValue, array, index + 1, combineNumbers, ++count);
        }

    }
}
