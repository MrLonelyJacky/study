package thinkingJava.innerClass.FactoryMethos;

/**
 * Created by 15151 on 2019/4/8.
 * 用匿名内部类的形式改造工厂方法模式
 * 用这种匿名内部类的形式，构造器可以私有化
 */
public class Implementational21 implements Service2{

    private Implementational21() {
    }

    @Override
    public void method1() {
        System.out.println("implemetational21 method1");
    }

    @Override
    public void method2() {
        System.out.println("implementational22 method2");
    }

    /**
     * 将工厂植入内部 代码十分简便，而且封闭性很好
     * @return
     */
    static ServiccFactory2 getServiceFactory2(){
        return new ServiccFactory2() {
            @Override
            public Service2 getService2() {
                return new Implementational21();
            }
        };
    }
}
