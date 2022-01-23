package thinkingJava.Reflect.agent;

/**
 * Created by 15151 on 2019/4/25.
 * 静态代理类  也可以理解为装饰类
 * 缺点是只能对某个具体的被代理类进行代理
 */
public class SimplyParoxy implements Interface {
    private Interface realObject;

    public SimplyParoxy(Interface realObject) {
        this.realObject = realObject;
    }

    @Override
    public void doSomething() {
        System.out.println("proxy do sth");
        realObject.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("proxy do sth else" + arg);
        realObject.somethingElse(arg);
    }
}
