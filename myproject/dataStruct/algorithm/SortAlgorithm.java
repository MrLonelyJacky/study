package dataStruct.algorithm;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by vinci on 2019/9/24.
 * 一些经典的排序算法
 *
 * @author jacky
 */
public class SortAlgorithm {
    //直接插入排序 适用于数据量小基本有序序列 最好的情况(本身就有序)下时间复杂度O(n)
    //思路 每趟比较当前元素和前一个元素的大小，若小于则需要排序  排序过程：移动数组到指定位置（大于等于该值元素后的位置）
    //稳定、平均时间复杂度O(n*n)
    public static void insertSort(int[] arrays) {
        int i, j;
        for (i = 1; i < arrays.length; i++) {
            //小于则需要排序
            if (arrays[i] < arrays[i - 1]) {
                int key = arrays[i];
                for (j = i - 1; j >= 0 && arrays[j] > key; j--) {
                    arrays[j + 1] = arrays[j];
                }
                //此时j--所以j到了大于等于该值元素的位置，但是我们要放到后面，这样同时也能保证稳定
                arrays[j + 1] = key;
            }
            System.out.println(Arrays.toString(arrays));
        }
    }

    /**
     * 折半插入排序
     *
     * @param arrays
     */
    public static void binarySort(int[] arrays) {
        int i, j;
        for (i = 1; i < arrays.length; i++) {
            int low = 0;
            int high = i - 1;
            int key = arrays[i];
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arrays[mid] > key) {
                    high = --mid;
                } else {
                    low = ++mid;
                }
            }
            for (j = i - 1; j >= high + 1; j--) {
                arrays[j + 1] = arrays[j];
            }
            arrays[j + 1] = key;
        }
    }

    //希尔排序 思路：确定增量gap 以增量为单位分组 增量数即分组数 初始增量gap = len/2
    //分组数 = len/(len/gap)=gap 对每组进行插入排序  然后缩小增量 重复上述过程
    //直到最后一次增量为1 即一组时进行增最后一组插入排序
    public static void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
            //对每组进行插入排序
            for (int i = gap; i < array.length; i++) {
                shellInsert(array, gap, i);
            }
        }
    }

    //下面的代码和插入排序很类似
    private static void shellInsert(int[] array, int gap, int i) {
        int key = array[i];
        int j;
        for (j = i - gap; j >= 0 && array[j] > key; j = j - gap) {
            array[j + gap] = array[j];
        }
        array[j + gap] = key;
    }

    /**
     * 冒泡排序
     * 特点：稳定 空间复杂度小 O(1)
     *
     * @param array
     */
    //冒泡排序 交换排序的一种算法(稍微做了点优化)
    public static void bubbleSort(int[] array) {
        //外层表示排序次数 n个数n-1次排序就是有序序列
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;//用来标记是否已经是有序序列，是的话则不需要进行下面交换操作
            for (int j = 0; j < array.length - 1; j++) {
                //内层循环表示交换
                int c;
                if (array[j] > array[j + 1]) {
                    c = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = c;
                    //只要交换了就说明不是有序序列
                    flag = true;
                }
            }
            if (!flag) {
                //有序则不用再进行后续操作
                return;
            }
            System.out.println(Arrays.toString(array));
        }
    }

    //快排 分治思想 空间复杂度O(n) 平均时间复杂度O(n*lgn)
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition - 1);
            quickSort(array, partition + 1, high);
        }
    }

    //快速排序的非递归实现
    public static void quickSortStack(int[] array, int low, int high) {
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()) {
            Integer high1 = stack.pop();
            Integer low1 = stack.pop();
            int partition = partition(array, low1, high1);

            if (partition + 1 < high1) {
                stack.push(partition + 1);
                stack.push(high1);
            }
            if (partition - 1 > low1) {
                stack.push(low1);
                stack.push(partition - 1);
            }
        }
    }

    //快排 分区 思路：以数组第一位为基准 先移动右哨兵向左j-- 找到小于基准数停下
    //再移动左哨兵向右i++，找到大于基准数停下  交换哨兵下的数字
    private static int partition(int[] array, int low, int high) {
        int key = array[low];//基准
        int index = low;
        while (low < high) {
            while (low < high && array[high] >= key) {
                high--;
            }
            while (low < high && array[low] <= key) {
                low++;
            }
            if (low < high) {
                //swap
                swap(array, low, high);
            }
        }
        swap(array, low, index);
        return low;
    }

    //选择排序 思路：每趟排序选择前面一个数为基数，找到数组中最小的数并交换顺序
    //这样每趟下来前面的数就是最小的数，最后生成有序序列
    //空间复杂度o1平均时间复杂度o(n*n)
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            //找到最小值
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            //交换
            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
            System.out.println(Arrays.toString(array));
        }
    }

    //TODO  基数排序 以及各项排序的性能比较

    /**
     * 归并排序思路：先分再治理 1、递归拆分
     * 2、合并(准备一个辅助数组，防止递归时不断开辟空间)
     *
     * @param array
     */
    public static void mergeSort(int[] array, int[] auxiArray, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, auxiArray, left, mid);//左递归 分
            mergeSort(array, auxiArray, mid + 1, right);//右递归
            merge(array, auxiArray, left, mid, right);
        }
    }

    //合并操作
    private static void merge(int[] array, int[] auxiArray, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = 0;//辅助数组指针
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                auxiArray[t++] = array[i++];
            } else {
                auxiArray[t++] = array[j++];
            }
        }
        //下面两段代码实际上只会执行其中之一
        while (i <= mid) {
            auxiArray[t++] = array[i++];
        }
        while (j <= right) {
            auxiArray[t++] = array[j++];
        }
        //将辅助数组中的有序数据拷贝到原数组中
        t = 0;
        while (left <= right) {
            array[left++] = auxiArray[t++];
        }
    }


    /**
     * 堆排序 1.构造大根堆 2.交换最后元素和首元素（最后元素变为最大值过程）
     * 3.调整堆结构满足大根堆
     */
    public static void heapSort(int[] array) {
        //构造大根堆 完全二叉树的最后一个非叶子节点开始逐渐往上构造
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        //交换位置
        for (int j = array.length - 1; j >= 0; j--) {
            swap(array, 0, j);
            adjustHeap(array, 0, j);
        }
    }

    //交换位置
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //调整对结构 当前节点 及其下节点调整
    private static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public static int[] countSort(int[] A) {
        // 找出数组A中的最大值、最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : A) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // 初始化计数数组count
        // 长度为最大值减最小值加1
        int[] count = new int[max - min + 1];
        // 对计数数组各元素赋值
        for (int num : A) {
            // A中的元素要减去最小值，再作为新索引
            count[num - min]++;
        }
        // 计数数组变形，新元素的值是前面元素累加之和的值
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        // 创建结果数组
        int[] result = new int[A.length];
        // 遍历A中的元素，填充到结果数组中去，从后往前遍历
        int lastIndex = A.length - 1;
        for (int j = lastIndex; j >= 0; j--) {
            //表示A[j]在对应计数数组中的下标位置
            int countIndex = A[j] - min;
            //表示结果数组的位置 等于计数数组中的排名-1
            int rank = count[countIndex] - 1;
            result[rank] = A[j];
            count[countIndex]--;
        }
        return result;
    }

    public static void main(String[] args) {
        //int[] array = {8,7,6,4,5};
        //bubbleSort(array);
        //int[] array = {2,5,1,3,6};
        //nsertSort(array);
        //int[] array = {4,1,3,6,2,5};
        //selectSort(array);
        int[] array = {90, 99, 95, 94, 95};
        System.out.println(Arrays.toString(countSort(array)));
        //binarySort(array);
        //System.out.println(Arrays.toString(array));
        //shellSort(array);
        //quickSort(array, 0, array.length - 1);
        //quickSortStack(array, 0, array.length - 1);
        //heapSort(array);
        //辅助数组
        /*int[] auxiArray = new int[array.length];
        mergeSort(array, auxiArray, 0, array.length - 1);*/
        //System.out.println(Arrays.toString(array));
        BigDecimal bigDecimal = new BigDecimal("0.0");
        BigDecimal bigDecimal2 = new BigDecimal("0.00");


        List<String> originList = new ArrayList<>();
        originList.add("hello");
        originList.add("world");
        originList.add("java");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=originList.size()-1;i>=0;i--){
            stringBuilder.append(originList.get(i));
        }
        System.out.println(stringBuilder.toString());
    }
}
