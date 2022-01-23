package concurrent.chapter13;

/**
 * jmm如何保证特性呢？
 * 1、jmm与原子性 对基本类型和引用类型的变量赋值和读取都是原子性的
 * 1.1 x=10赋值原子性的  线程会将数值写入工作内存然后写入主存
 * 1.2 y=x是非原子性的  原因在于先要读取x 再赋值 读取的过程是从主存或者工作内存中
 * 读取（工作内存有直接读工作内存） 然后再把y的值写入主存中
 * 1.3 y++是非原子性的  原因在于先去读取主存或者工作内存的值 再+1  再更新到主存中
 * 例子看notAutomic 方法
 * 总结：a、多个原子操作就非原子了  b、简单的赋值是原子的，多步骤的如先要读取再更新的就非原子
 * c syn和juc中的auto工具可以保证原子性
 *
 * jmm与可见性
 *
 *
 */
public class JmmWithFeatures {
    private void notAutomic() {
        //原子
        int x = 10;
        //非原子
        x++;
        //非原子
        int y = x;
        //非原子
        int z =y + 1;
    }
}
