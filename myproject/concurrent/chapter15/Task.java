package concurrent.chapter15;

/**
 * Created by 15151 on 2020/5/13.
 * task接口函数
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}
