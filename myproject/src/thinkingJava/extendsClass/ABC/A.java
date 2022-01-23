package thinkingJava.extendsClass.ABC;

import thinkingJava.Map.Person;

/**
 * Created by 15151 on 2019/2/23.
 */
public class A extends C{
    private final Person person = new Person();
    public static int b;
    private int i;
    private void sing(){
        System.out.println("aaa");
    }
    public static void hehe(){
        b=3;
    }
    public static void main(String[] args) {
        A a =new A();
        A a1 = (A) new C();//向下转型
        System.out.println(a.getA());
        a.i=3;
        a.sing();
        a.toSay();
        a.person.setName("a");
    }

}
