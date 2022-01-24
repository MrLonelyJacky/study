package thinkingJava.genericParadigm.mixedType;

/**
 * Created by 15151 on 2019/5/6.
 */
public class Mixins {
    public static void main(String[] args) {
        Mixin mixin = new Mixin();
        Mixin mixin1 = new Mixin();
        mixin.set("test 1");
        mixin.set("test 2");
        System.out.println(mixin.get()+" "+ mixin);
    }
}
