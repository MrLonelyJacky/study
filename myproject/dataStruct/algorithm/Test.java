package dataStruct.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/9/1.
 * 我写了两种实现方式  一种先找 组合数  一种直接递归
 */
public class Test {
    private static List<Integer[]> doubleList = new ArrayList<>();
    private static List<List<Integer>> tribeList = new ArrayList<>();

    public static void main(String[] args) {
        Integer[] combineNumbers = new Integer[3];
        int[] array = {1, 2, 3, 4, 5,6};

        /*get(3,array,0,combineNumbers);
        for (Integer[] list : doubleList) {
            for (Integer i : list) {
                System.out.println(i);
            }
        }*/
        getCombineNum(0, 6, new Integer[5], array,0);
        for (Integer[] list : doubleList) {
            for (Integer i : list) {
                System.out.println(i);
            }
        }
    }

    //拿取组合数为countNum的组合数
    private static void get(int countNum, int[] array, int index, Integer[] list) {
        //1,2,3,4,5
        for (int i = index; i < array.length; i++) {
            list[countNum - 1] = array[i];
            if (countNum == 1) {
                Integer[] integers = new Integer[list.length];
                System.arraycopy(list, 0, integers, 0, list.length);
                doubleList.add(integers);

            } else {
                get(--countNum, array, ++index, list);
                countNum++;
            }
        }
    }

    /**
     * 直接递归实现组合数的查找
     * @param start
     * @param combineNum
     * @param combineNumbers
     * @param array
     * @param count
     */
    private static void getCombineNum(int start, int combineNum, Integer[] combineNumbers, int[] array, int count) {
        //1,2,3,4,5
        for (int i = start; i < array.length; i++) {
            combineNumbers[count] = array[i];
            int dValue = combineNum - array[i];
            if (dValue < 0) {
                return;
            }
            if (dValue == 0) {
                Integer[] integers = new Integer[count+1];
                System.arraycopy(combineNumbers, 0, integers, 0, count+1);
                doubleList.add(integers);
                return;
            }
            getCombineNum(i + 1, dValue, combineNumbers, array, ++count);
            count--;
        }
    }
}
