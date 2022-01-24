package thinkingJava.innerClass.controlFramework;

/**
 * Created by 15151 on 2019/4/13.
 */
public class GreenHouseController {
    public static void main(String[] args) {
        GreenHouseControls controls = new GreenHouseControls();
        controls.addEvent(controls.new Bell(900));
        Event[] events = {controls.new LightOn(200), controls.new LightOff(400)
                , controls.new WaterOn(600), controls.new WaterOff(800)
        };
        controls.addEvent(controls.new Restart(2000, events));

        controls.run();
    }
}
