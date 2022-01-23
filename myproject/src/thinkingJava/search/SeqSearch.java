package thinkingJava.search;

/**
 * Created by 15151 on 2019/9/12.
 * 顺序表的一些查找方法
 */
public class SeqSearch {
    /**
     * 折半查找判定树
     *
     * @param key
     * @param search
     */
    private static int binarySearch(int key, int[] search) {
        int low = 0;
        int high = search.length - 1;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (key == search[mid]) {
                return mid;
            } else if (key < search[mid]) {
                high = --mid;
            } else {
                low = ++mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {7, 10, 13, 16, 19, 29, 32, 33, 37, 41, 43};
        System.out.println(binarySearch(13, array));
        System.out.println(1 / 2);
    }
}
