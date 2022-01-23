package concurrent.chapter13;

/**
 * 并发编程的三个重要特性
 * 1.原子性  synchronized 和原子类型变量 可以保证原子性
 * 2.可见性 chapter12中的案例说明里volatile可以保证原子性
 * 3、有序性 我们写的代码顺序未必是jvm执行的顺序 单线程的情况下虽然顺序不一定
 * 但是结果是预期的 多线程则未必，有的可能先执行a步骤 有的则可能先执行b步骤
 * 对于有数据依赖关系的指令是严格按照顺序来的 比如下面的方法runByOrder
 */
public class Features {
    /**
     * 对于依赖关系的 y=x+1;一定在x++之后执行
     */
    private void runByOrder() {
        int x = 10;
        int y = 0;
        x++;
        y = x + 1;
    }
}
