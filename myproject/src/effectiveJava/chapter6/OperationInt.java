package effectiveJava.chapter6;

/**
 * Created by 15151 on 2020/2/3.
 * 虽然枚举不可拓展，但是可以用接口实现拓展方式
 */
public interface OperationInt {
    double apply(double x, double y);
}
