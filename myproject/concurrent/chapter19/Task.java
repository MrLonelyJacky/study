package concurrent.chapter19;

/**
 * Created by 15151 on 2020/5/27.
 * 用于提供计算逻辑
 */
@FunctionalInterface
public interface Task<IN, OUT> {
    /**
     * 给定一个参数返回结果
     *
     * @param input
     * @return
     */
    OUT get(IN input);
}
