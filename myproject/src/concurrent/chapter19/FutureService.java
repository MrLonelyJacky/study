package concurrent.chapter19;

/**
 * Created by 15151 on 2020/5/27.
 * 该接口主要用于提交任务，并支持两种提交方式
 */
public interface FutureService<IN, OUT> {
    //提交不需要返回值的任务，Future.get返回的将是null
    Future<Void> submit(Runnable runnable);

    //提交需要返回值的任务
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    //支持回调机制
    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);

    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
