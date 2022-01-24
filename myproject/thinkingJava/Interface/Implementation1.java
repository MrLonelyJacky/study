package thinkingJava.Interface;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Implementation1 implements Service{

    @Override
    public void method1() {
        System.out.println("implement1 method1");
    }

    @Override
    public void method2() {
        System.out.println("implement1 method2");
    }
}
