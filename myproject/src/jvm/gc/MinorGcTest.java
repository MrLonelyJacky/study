package jvm.gc;

/**
 * Created by 15151 on 2019/5/29.
 * -verbose:gc
 -Xms20M
 -Xmx20M
 -Xmn10M
 -XX:+PrintGCDetails
 -XX:SurvivorRatio=8
 -XX:HeapDumpPath=d:\dump
 */
public class MinorGcTest {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {

    }

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        //allocation1 = null;
        allocation2 = new byte[2 * _1MB];
        //allocation2 = null;
        allocation3 = new byte[2 * _1MB];
       //allocation3 = null;
        allocation4 = new byte[4 * _1MB];
    }
}
