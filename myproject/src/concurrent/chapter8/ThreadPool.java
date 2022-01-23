package concurrent.chapter8;

/**
 * Created by 15151 on 2020/4/28.
 */
public interface ThreadPool {
    //提交任务到线程池
    void execute(Runnable runnable);

    //关闭线程池
    void shutDown();

    //获取初始化线程池大小
    int getInitSize();

    //获取线程池最大线程数
    int getMaxSize();

    //获取线程池的核心数量
    int getCoreSize();

    //获取线程池缓存任务队列的大小
    int getQueueSize();

    //获取线程池中活跃线程的数量
    int getActiveCount();

    //查看线程池是否关闭
    boolean isShutDown();
}
