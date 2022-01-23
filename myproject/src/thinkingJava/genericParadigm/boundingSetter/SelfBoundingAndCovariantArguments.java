package thinkingJava.genericParadigm.boundingSetter;

/**
 * Created by 15151 on 2019/5/6.
 */
public class SelfBoundingAndCovariantArguments {
    void test(Setter s1, Setter s2, SelfBoundingSetter sb1, SelfBoundingSetter sb2) {
        s1.set(s2);
        // 编译器不能识别将基类型当做参数传递给set的尝试，
        // 因为没有任何方法具有这样的签名。事实上，这个参数已经被覆盖。
        //s1.set(sb1);
        sb1.set(s1);
        sb1.set(sb2);
    }

    public static void main(String[] args) {

    }
}
