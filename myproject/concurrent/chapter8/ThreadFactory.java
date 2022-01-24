package concurrent.chapter8;

/**
 * Created by 15151 on 2020/4/28.
 * 工厂方法模式 线程工厂
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
