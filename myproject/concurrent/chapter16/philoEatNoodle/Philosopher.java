package concurrent.chapter16.philoEatNoodle;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 15151 on 2020/5/15.
 * 哲学家类
 */
public class Philosopher extends Thread {


    private final AutomicChop automicChop;

    public Philosopher(AutomicChop automicChop) {
        this.automicChop = automicChop;
    }


    @Override
    public void run() {
        while (true) {
            /*synchronized (leftChop) {
                System.out.println(getName() + "take up" + leftChop.getName());
                synchronized (rightChop) {
                    System.out.println(getName() + "take up" + rightChop.getName());
                    System.out.println("eat.....");
                }
            }
            System.out.println("finish");
            try {
                //哲学家休息休息
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (automicChop){

            }
        }

    }

}
