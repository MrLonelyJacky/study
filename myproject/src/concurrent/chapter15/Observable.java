package concurrent.chapter15;

/**
 * Created by 15151 on 2020/5/13.
 * 该接口用来屏蔽Thread中的其他方法，减少细节的暴露
 */
public interface Observable {
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR;
    }

    //获取当前任务的生命周期0
    Cycle getCycle();

    //定义线程的启动方法，主要是为了屏蔽Thread的其他方法
    void start();

    //定义打断方法，作用和start一致 屏蔽其他方法
    void interrupt();
}
