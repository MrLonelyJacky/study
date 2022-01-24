package thinkingJava.genericParadigm.mixedType;

/**
 * Created by 15151 on 2019/5/6.
 * 混型过多时会导致代码非常复杂，考虑采用装饰着模式
 */
public class Mixin extends BasicImp implements TimeStamped, SerialNumbered {
    private TimeStamped timeStamped = new TimeStampedImp();
    private SerialNumbered serialNumbered = new SerialNumberedImp();

    @Override
    public long getStamp() {
        return timeStamped.getStamp();
    }

    @Override
    public long getSerialNumbered() {
        return serialNumbered.getSerialNumbered();
    }
}
