package thinkingJava.Interface;

/**
 * Created by 15151 on 2019/4/6.
 */
public class Factories {
    public static void serviceConsumer(ServiceFactory factory){
        Service service = factory.getService();
        service.method1();
        service.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(new Implementation2Factory());
        serviceConsumer(new Implementation1Factory());

    }
}
