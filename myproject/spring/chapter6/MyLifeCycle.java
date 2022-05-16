package spring.chapter6;

import org.springframework.context.Lifecycle;

public class MyLifeCycle implements Lifecycle {
    /**
     * 运行状态
     */
    private volatile boolean running = false;

    @Override
    public void start() {
        System.out.println("容器启动后执行MyLifeCycle操作...");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("收到关闭容器的信号MyLifeCycle操作...");
        running = false;
    }

    @Override
    public boolean isRunning() {
        System.out.println("检查MyLifeCycle组件的运行状态：" + running);
        return running;
    }
}
