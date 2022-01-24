package dataStruct.blockSearch;

import java.util.ArrayList;

/**
 * Created by 15151 on 2019/2/13.
 */
public class BlockSearch {
    /**
     * 辅助数组用来标记list数组
     */
    private int[] index;
    private ArrayList<Integer>[] lists;

    /**
     * 初始化索引
     *
     * @param index
     */
    public BlockSearch(int[] index) {
        if (index != null && index.length > 0) {
            this.index = index;
            this.lists = new ArrayList[index.length];
            //初始化lists
            for (int i = 0; i < lists.length; i++) {
                lists[i] = new ArrayList<Integer>();
            }
        }
    }

    public void insert(int value) {
        //根据二叉查找找到下标
        int search = binSearch(index, value);
        lists[search].add(value);
    }

    /**
     * 二分查找的非递归实现，这里不用递归的方式
     * 要求arr数组是有序的
     *
     * @param arr
     */
    public static int binSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;
        while (low <= high) {
            if (key > arr[mid]) {
                low = mid + 1;
            }
            if (key < arr[mid]) {
                high = mid - 1;
            }
            if (arr[mid] == key) {
                return mid;
            }
            mid = (low + high) / 2;
        }
        return low;
    }

    /**
     * 顺序查找
     */
    public boolean search(int value) {
        int i = binSearch(index, value);
        for (int j = 0; j < lists[i].size(); j++) {
            if (lists[i].get(j) == value) {
                System.out.println(String.format("查找元素为第: %d块  第%d个 元素",  i+1,j+1));
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] index = new int[]{10, 20, 30};
        BlockSearch blockSearch = new BlockSearch(index);
        blockSearch.insert(5);
        blockSearch.insert(6);
        blockSearch.insert(15);
        blockSearch.insert(16);
        blockSearch.insert(25);
        blockSearch.insert(26);
        blockSearch.search(6);
    }
}
