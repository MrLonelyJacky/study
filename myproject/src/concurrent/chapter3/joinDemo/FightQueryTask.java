package concurrent.chapter3.joinDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by vinci on 2020/4/10.
 */
public class FightQueryTask extends Thread implements FightQuery {
    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airLine, String origin, String destination) {
        super("[" + airLine + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        this.flightList.add(getName() + "-" + randomVal);
        System.out.println("query success:" + getName());
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}
