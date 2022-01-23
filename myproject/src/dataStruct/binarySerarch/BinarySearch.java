package dataStruct.binarySerarch;

/**
 * Created by 15151 on 2019/2/13.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 8, 9};
        System.out.println(binSearch(arr, 0));
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
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key > arr[mid]) {
                low = mid + 1;
            }
            if (key < arr[mid]) {
                high = mid - 1;
            }
            if (arr[mid] == key) {
                return mid;
            }
        }
        return -1;
    }
}
