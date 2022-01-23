package concurrent.chapter4;

/**
 * Created by 15151 on 2020/3/27.
 * 同步代码块解决之前的线程安全问题
 */
public class TicketWindowRunnable implements Runnable {
    /**
     * 这里已经不用static修饰共享了，将线程和业务逻辑分开来就行了
     * 功能单一职责分明
     */
    private int index = 1;//不做static修饰
    private static final int MAX_NUM = 50;
    private static final Object MUTEX = new Object();
    @Override
    public void run() {
        synchronized (MUTEX){
            while (index <= MAX_NUM) {
                System.out.println("当前机：" + Thread.currentThread() + "当前票数：" + index);
                index++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread thread1 = new Thread(ticketWindowRunnable, "window1");
        Thread thread2 = new Thread(ticketWindowRunnable, "window2");
        Thread thread3 = new Thread(ticketWindowRunnable, "window3");
        Thread thread4 = new Thread(ticketWindowRunnable, "window4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
