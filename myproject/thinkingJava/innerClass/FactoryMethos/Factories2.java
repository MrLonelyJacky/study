package thinkingJava.innerClass.FactoryMethos;

/**
 * Created by 15151 on 2019/4/8.
 */
public class Factories2 {
    public static void main(String[] args) {
        userFactory(Implementational21.getServiceFactory2());
        userFactory(Implementational22.getServiceFactory2());
    }

    public static void userFactory(ServiccFactory2 serviccFactory2) {
        Service2 service2 = serviccFactory2.getService2();
        service2.method1();
        service2.method2();
    }
}
