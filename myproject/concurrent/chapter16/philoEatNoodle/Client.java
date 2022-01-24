package concurrent.chapter16.philoEatNoodle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2020/5/15.
 */
public class Client {
    public static void main(String[] args) {
        Chopstick chopstick1 = new Chopstick("k1");
        Chopstick chopstick2 = new Chopstick("k2");
        Chopstick chopstick3 = new Chopstick("k3");
        Chopstick chopstick4 = new Chopstick("k4");
        Chopstick chopstick5 = new Chopstick("k5");
        /*new Philosopher(chopstick1, chopstick2).start();
        new Philosopher(chopstick2, chopstick3).start();
        new Philosopher(chopstick3, chopstick4).start();
        new Philosopher(chopstick4, chopstick5).start();
        new Philosopher(chopstick5, chopstick1).start();*/

    }
}
