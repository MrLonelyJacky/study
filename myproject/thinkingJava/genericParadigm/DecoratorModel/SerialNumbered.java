package thinkingJava.genericParadigm.DecoratorModel;

/**
 * Created by 15151 on 2019/5/6.
 */
public class SerialNumbered extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter++;

    public SerialNumbered(Basic basic) {
        super(basic);
    }

    public long getSerialNumber(){
        return serialNumber;
    }
}
