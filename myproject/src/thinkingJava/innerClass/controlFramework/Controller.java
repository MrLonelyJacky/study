package thinkingJava.innerClass.controlFramework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/4/11.
 */
public class Controller {
    //如果list是队列的话  那就有趣了
    private List<Event> eventList = new ArrayList<>();

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void run() {
        while (eventList.size() > 0) {
            ArrayList<Event> eventsss = new ArrayList<>(eventList);
            for (Event event : eventsss) {
                if (event.ready()) {
                    System.out.println(event);
                    event.action();
                    eventList.remove(event);
                }
            }
        }
    }


    public static void main(String[] args) {

    }
}
