package concurrent.chapter19;

/**
 * Created by 15151 on 2020/5/27.
 * future的一个实现
 */
public class FutureTask<T> implements Future<T> {
    //计算结果
    private T result;
    //任务是否完成
    private boolean isDone = false;
    //定义锁对象
    private final Object LOCK = new Object();


    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK) {
            while (!isDone) {
                //如果当前任务未完成，调用get将被挂起并进入阻塞状态
                LOCK.wait();
            }
            return result;
        }
    }

    //该方法用于为FutureTask设置计算结果
    protected void finish(T result) {
        synchronized (LOCK) {
            if (isDone) {
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
