package concurrent.chapter15;

/**
 * Created by 15151 on 2020/5/13.
 * 生命周期接口的空实现 主要是为了让使用者保持Thread的使用习惯
 */
public class EmptyLifeCycle<T> implements TaskLifeCycle<T> {
    @Override
    public void onStart(Thread thread) {
        //do nothing
    }

    @Override
    public void onRunning(Thread thread) {
        //do nothing
    }

    @Override
    public void onFinish(Thread thread, T result) {
        //do nothing
    }

    @Override
    public void onError(Thread thread, Exception e) {
        //do nothing
    }
}
