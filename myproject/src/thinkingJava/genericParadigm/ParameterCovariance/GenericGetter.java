package thinkingJava.genericParadigm.ParameterCovariance;

/**
 * Created by 15151 on 2019/5/6.
 */
public interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}
