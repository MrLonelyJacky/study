package thinkingJava.innerClass.whyUse;

/**
 * Created by 15151 on 2019/4/8.
 */
public class Y implements A {
    B makeB() {
        return new B() {
        };
    }
}
