package jvm.chapter7;

/**
 * Created by 15151 on 2020/4/30.
 * 通过子类引用父类的静态域，不会使得子类初始化
 * 对于静态字段，只有定义这个字段的类才会被初始化
 */
public class NotInit {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
