package thinkingJava.genericParadigm.modelMethod;

/**
 * Created by 15151 on 2019/4/30.
 */
public class Creator extends GenericWithCreate<X1>{
    @Override
    X1 create() {
        return  new X1();
    }
    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}
