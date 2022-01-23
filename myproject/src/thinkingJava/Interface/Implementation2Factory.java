package thinkingJava.Interface;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Implementation2Factory implements ServiceFactory {
    @Override
    public Service getService() {
        return new Implementation2();
    }
}
