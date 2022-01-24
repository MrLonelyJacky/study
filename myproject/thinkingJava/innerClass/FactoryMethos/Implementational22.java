package thinkingJava.innerClass.FactoryMethos;

/**
 * Created by 15151 on 2019/4/8.
 */
public class Implementational22 implements Service2{
    @Override
    public void method1() {
        System.out.println("implemementational22 method1");
    }

    @Override
    public void method2() {
        System.out.println("implemementational22 method2");
    }

    public static ServiccFactory2 getServiceFactory2(){
        return new ServiccFactory2() {
            @Override
            public Service2 getService2() {
                return new Implementational22();
            }
        };
    }
}
