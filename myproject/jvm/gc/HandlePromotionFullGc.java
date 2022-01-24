package jvm.gc;

/**
 * Created by 15151 on 2019/5/30.
 * 空间分配担保，指的是minorGc后  存在大对象超过设置的阈值
 * 或者对象太多导致To区内存爆了，直接进入老年代
 * 整个担保过程是这样的，youngGc发生前比较老生代剩余内存和伊甸园区对象总和大小
 * 如果剩余内存小于伊甸园区的对象总和则是这次的yongGc不安全，反之安全
 * 那就要设置是否允许担保失败，如果允许则进行判断  比较平均每次分配到老生代的对象大小和老生代剩余空间大小
 * 平均值小于剩余空间则设置允许冒险执行youngGC即使这次youngGC是有风险的，因为平均值不能代表一切情况，
 * 反之则设置不允许冒险直接进行fullGC清理老生代，保证分配空间
 */
public class HandlePromotionFullGc {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        //分配
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];

    }
}
