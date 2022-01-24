package thinkingJava.innerClass.whyUse;

/**
 * Created by 15151 on 2019/4/8.
 */
public class MutiInterfaces {
    static void takeA(A a){
    }
    static void takeB(B b){
    }

    public static void main(String[] args) {
        X x = new X();
        takeA(x);
        takeB(x);
        Y y = new Y();
        takeB(y.makeB());
        takeA(y);
    }
}
