package effectiveJava.chapter4;

/**
 * 使可变性最小化
 * 不可变类指其实例不能被修改的类，每个实例的所有信息必须在创建该实例的时候就提供
 * 并在整个对象生命周期内不变
 * final 复数类  注意：1.不提供设置方法 2.类不该被拓展 3.所有域都是final
 * 4.所有域都private 5.确保对于可变性组件互斥访问
 * 不可变对象本质上是线程安全的，不需要同步  当然也不需要拷贝
 * 不可变对象唯一的缺点就是当一个大对象，你稍微改动一点就需要重新创建一个大对象
 * 尤其当有多步骤修改的时候会产生大量无用的大对象
 * 解决方法：1.若能预料客户端要进行哪些复杂的操作，提供一个包级私有的可配套类即可
 * 2.无法预测则提供一个共有的可配套类 如String  StringBuilder
 * 当然还有另一种实现不可变类的方式是私有化构造方法 并提供一个静态工厂 这种方式更灵活
 */
public final class Complex {
    private final double re;//实部
    private final double im;//虚部
    /**
     * 不可变对象安全所以自由共享 尤其对于频繁用到的值
     */
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);

    /**
     * 不可变类可以被进一步拓展，如静态工厂，用以缓存实例
     * @param re
     * @param im
     */

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart() {
        return this.re;
    }

    public double imaginaryPart() {
        return this.im;
    }

    /**
     * 函数方法：方法返回一个函数结果但不影响原来的对象
     * 方法名不应该叫add
     *
     * @param c
     * @return
     */
    public Complex plus(Complex c) {
        return new Complex(this.re + c.re, this.im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(this.re - c.re, this.im - c.im);
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Complex)) {
            return false;
        }
        Complex complex = (Complex) o;
        return Double.compare(this.im, complex.im) == 0 && Double.compare(this.re, complex.re) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(this.re) + Double.hashCode(this.im);
    }

    public static void main(String[] args) {

    }
}
