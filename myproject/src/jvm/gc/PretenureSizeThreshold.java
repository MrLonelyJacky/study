package jvm.gc;

/**
 * Created by 15151 on 2019/5/29.
 */
public class PretenureSizeThreshold {


    public static void testAllocation() {
        byte[] allocation4 = new byte[4 * 1024 * 1024];
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
