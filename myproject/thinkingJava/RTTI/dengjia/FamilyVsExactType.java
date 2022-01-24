package thinkingJava.RTTI.dengjia;

/**
 * Created by 15151 on 2019/4/23.
 */
public class FamilyVsExactType {
    static void test(Object x){
        System.out.println(x.getClass());
        System.out.println(x instanceof Base);
        System.out.println(x instanceof Derived);
        System.out.println(Base.class.isInstance(x));

    }

    public static void main(String[] args) {
        test(new Base());
    }
}
