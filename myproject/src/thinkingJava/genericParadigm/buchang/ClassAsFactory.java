package thinkingJava.genericParadigm.buchang;

/**
 * Created by 15151 on 2019/4/30.
 * 这种殷实的工厂 sun的小伙伴不建议是用，因为会报错
 * 建议使用显示的工厂   见案例FactoryI
 */
public class ClassAsFactory<T> {
    private T x;
    public ClassAsFactory(Class<T> tClass) {
        try {
            x = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
