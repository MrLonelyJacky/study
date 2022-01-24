package thinkingJava.genericParadigm.mixedType;

import java.util.Date;

/**
 * Created by 15151 on 2019/5/6.
 */
public class TimeStampedImp implements TimeStamped {
    private final long timeStamp;

    public TimeStampedImp() {
        this.timeStamp = new Date().getTime();
    }

    @Override
    public long getStamp() {
        return timeStamp;
    }
}
