package concurrent.chapter23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 程序员坐车去同一目的地
 */
public class ProgrammerTravel extends Thread {
    //门阀
    private final Latch latch;
    private final String programmer;
    private final String transportation;

    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.print(programmer + "start take transport" + transportation);
        //坐车去目的地要花费时间
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + "arrived by" + transportation);
        //完成任务时 减一
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch,"a","car").start();
        new ProgrammerTravel(latch,"b","bike").start();
        new ProgrammerTravel(latch,"c","bus").start();
        new ProgrammerTravel(latch,"d","walk").start();
        latch.await();

    }
}
