package thinkingJava.genericParadigm.ParameterCovariance;

/**
 * Created by 15151 on 2019/5/6.
 */
public class GenericAndReturnTypes {
    void test(Getter getter) {
        Getter getter1 = getter.get();
        GenericGetter genericGetter = getter.get();
    }
}
