package thinkingJava.innerClass;

/**
 * Created by 15151 on 2019/2/15.
 */
public class InnerTest {
    public static void main(String[] args) {
        Outer outer = new Outer("");
        //这里必须要加Outer 而且右边不可以直接创建Inner对象 必须经过outer
        Outer.Inner inner = outer.getInner();
    }
}
