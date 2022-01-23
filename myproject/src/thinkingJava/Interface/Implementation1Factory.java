package thinkingJava.Interface;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Implementation1Factory implements ServiceFactory{
    @Override
    public Service getService() {
        return new Implementation1();
    }
}
