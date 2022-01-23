package concurrent.chapter15;

/**
 * Created by 15151 on 2020/5/13.
 * 任务监听回调 事件订阅者
 */
public interface TaskLifeCycle<T> {
    //任务启动时出发 回调
    void onStart(Thread thread);
    //运行时出发 回调
    void onRunning(Thread thread);
    //完成时触发 回调
    void onFinish(Thread thread, T result);
    //报错时触发 回调
    void onError(Thread thread, Exception e);

}
