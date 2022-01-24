package concurrent.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/10.
 * 倘若线程在blocked阶段则被中断时 会捕捉到中断信号，并且会擦除
 * 但是interrupt本身不会擦除，这和interrupted不同
 * interrupt标识
 */
public class ThreadisInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true){
                    //System.out.println("do nothing");
                    //do nothing  the following code if u document have different result
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("i am interrupted："+isInterrupted());
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        //wait time for interrupt 主线程调用了interrupt等待 thread的sleep方法捕捉到中断信号，并擦除interrupt标识
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(thread.isInterrupted());
    }
}
