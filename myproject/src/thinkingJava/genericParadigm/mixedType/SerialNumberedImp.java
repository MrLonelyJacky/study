package thinkingJava.genericParadigm.mixedType;

/**
 * Created by 15151 on 2019/5/6.
 */
public class SerialNumberedImp implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;

    @Override
    public long getSerialNumbered() {
        return serialNumber;
    }
}
