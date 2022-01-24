package thinkingJava.genericParadigm.displayFactory;

/**
 * Created by 15151 on 2019/4/30.
 */
public class IntegerFactory implements FactoryI<Integer> {
    @Override
    @SuppressWarnings("unchecked")
    public Integer create() {
        return new Integer(0);
    }
}
