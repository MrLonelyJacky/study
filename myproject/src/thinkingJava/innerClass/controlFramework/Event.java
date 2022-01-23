package thinkingJava.innerClass.controlFramework;

/**
 * Created by 15151 on 2019/4/9.
 */
public abstract class Event {
    private long eventTime;
    //final 必须初始化 这里使我奇怪的是抽象类不能实例化，为什么要有
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
    }

    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();
}
