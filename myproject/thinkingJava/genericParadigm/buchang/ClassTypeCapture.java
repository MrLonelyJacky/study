package thinkingJava.genericParadigm.buchang;

/**
 * Created by 15151 on 2019/4/30.
 */
public class ClassTypeCapture<T> {
    private Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        //通过字面量的方式 相当于
        Class c = Building.class;
        ClassTypeCapture<Building> capture = new ClassTypeCapture<>(Building.class);
        System.out.println(capture.f(new Building()));
        System.out.println(capture.f(new House()));
    }
}
