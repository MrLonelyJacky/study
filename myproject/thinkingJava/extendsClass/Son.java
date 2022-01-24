package thinkingJava.extendsClass;

/**
 * Created by 15151 on 2019/2/23.
 */
public class Son extends Father{
    public Son(String name) {
        //默认调用父类无参构造 ， 但父类构造有参
        super(name);
    }

    public static void main(String[] args) {
        Son son = new Son("aaa");
        son.say();
        System.out.println();
    }
}
