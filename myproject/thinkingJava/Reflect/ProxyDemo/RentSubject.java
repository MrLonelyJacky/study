package thinkingJava.Reflect.ProxyDemo;

/**
 * Created by 15151 on 2019/4/27.
 * 租户
 */
public class RentSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("i want my house rent");
    }

    @Override
    public void hell0(String string) {
        System.out.println("hell0 " + string);
    }
}
