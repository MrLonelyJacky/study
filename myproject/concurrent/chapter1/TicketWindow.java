package concurrent.chapter1;

/**
 * Created by 15151 on 2020/3/25.
 * to design a call number system
 * but has thread safe todo //TODO 后面会有方式改进该代码
 */
public class TicketWindow extends Thread {
    private final String name;

    private static final int MAX_NUM = 50;

    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX_NUM) {
            System.out.println("当前机：" + name + "当前票数：" + index);
            index++;
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new TicketWindow("window1");
        Thread thread2 = new TicketWindow("window2");
        Thread thread3 = new TicketWindow("window3");
        Thread thread4 = new TicketWindow("window4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
