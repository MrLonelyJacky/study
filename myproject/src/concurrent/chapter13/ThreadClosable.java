package concurrent.chapter13;

/**
 * Created by 15151 on 2020/5/12.
 * volatile使用场景
 * 1、开关控制利用可见性
 * 2、状态标记利用顺序性
 * 3、singleton设计模式的double-check
 */
public class ThreadClosable extends Thread {
    private volatile boolean started = true;

    @Override
    public void run() {
        while (started) {
            System.out.println("do work until latch");
        }
    }

    public void shutDown() {
        started = false;
    }

}
