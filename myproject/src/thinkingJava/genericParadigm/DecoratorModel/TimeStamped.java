package thinkingJava.genericParadigm.DecoratorModel;

import java.util.Date;

/**
 * Created by 15151 on 2019/5/6.
 */
public class TimeStamped extends Decorator {
    private final long timeStamp;

    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }

    public long getTimeStamp(){
        return timeStamp;
    }
}
