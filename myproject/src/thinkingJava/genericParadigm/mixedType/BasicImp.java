package thinkingJava.genericParadigm.mixedType;

/**
 * Created by 15151 on 2019/5/6.
 */
public class BasicImp implements Basic {
    private String value;

    @Override
    public String get() {
        return value;
    }

    @Override
    public void set(String val) {
        value = val;
    }
}
