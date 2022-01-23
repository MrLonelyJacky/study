package thinkingJava.innerClass.controlFramework;

/**
 * Created by 15151 on 2019/4/11.
 */
public class GreenHouseControls extends Controller {
    private boolean light = false;

    public class LightOn extends Event {
        //现在我明白了为什么 event作为一个抽象类还需要构造方法，是为了子类继承去用
        public LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = true;
        }

        @Override
        public String toString() {
            return "light on";
        }
    }

    public class LightOff extends Event {
        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = false;
        }

        @Override
        public String toString() {
            return "light off";
        }
    }

    private boolean water = false;

    public class WaterOn extends Event {
        public WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = true;
        }

        @Override
        public String toString() {
            return "water on";
        }
    }

    public class WaterOff extends Event {
        public WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }

        @Override
        public String toString() {
            return "water off";
        }
    }

    public class Bell extends Event{

        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return "bing";
        }

    }

    public class Restart extends Event {
        private Event[] events;

        public Restart(long delayTime, Event[] events) {
            super(delayTime);
            this.events = events;
            for (Event e: events) {
                addEvent(e);
            }
        }

        @Override
        public void action() {
            //将重启后的event 放入lsit中
            for (Event event : events){
                event.start();
                addEvent(event);
            }
            //重启该event  并放入list
            start();
            addEvent(this);
        }

        @Override
        public String toString() {
            return "restart";
        }
    }

    public static void main(String[] args) {

    }
}
