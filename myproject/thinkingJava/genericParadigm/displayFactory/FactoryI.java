package thinkingJava.genericParadigm.displayFactory;

/**
 * Created by 15151 on 2019/4/30.
 * 显示的工厂避免了补偿使用内建的工厂所带来的构造器问题
 */
public interface FactoryI<T> {
    T create();
}
