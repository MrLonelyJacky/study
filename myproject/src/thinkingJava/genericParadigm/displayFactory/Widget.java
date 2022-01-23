package thinkingJava.genericParadigm.displayFactory;

/**
 * Created by 15151 on 2019/4/30.
 */
public class Widget {
    public static class Factory implements FactoryI<Widget> {
        @Override
        public Widget create() {
            return new Widget();
        }
    }
}
