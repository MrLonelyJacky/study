package jvm.gc;

/**
 * Created by 15151 on 2019/5/23.
 * 采用引用计数算法gc  不能解决循环引用问题
 */
public class ReferenceCountingGc {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGc() {
        ReferenceCountingGc gc = new ReferenceCountingGc();
        ReferenceCountingGc gc1 = new ReferenceCountingGc();
        gc.instance = gc1;
        gc1.instance = gc;
        gc = null;
        gc1 = null;
        //假设在这里发生gc gc和gc1是否被回收
        System.gc();
    }

    public static void main(String[] args) {
        testGc();
    }
}
