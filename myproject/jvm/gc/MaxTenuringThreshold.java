package jvm.gc;

/**
 * Created by 15151 on 2019/5/30.
 */
public class MaxTenuringThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        //when to old  due to maxTenuring
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        //我在思考这个地方要不要置为null 因为该内存已经在下次分配新内存的时候不被引用了
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];

    }
}
