package concurrent.chapter8;

/**
 * Created by 15151 on 2020/4/28.
 */
public interface RunnableQueue {
    //将提交的任务缓存至队列
    void offer(Runnable runnable);

    //获取任务
    Runnable take() throws InterruptedException;

    //获取队列中的任务数量
    int size();
}
