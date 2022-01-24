package thinkingJava.innerClass.thisNew;

/**
 * Created by 15151 on 2019/2/17.
 */
public class NewTest {
    public static void main(String[] args) {
        DoThis doThis = new DoThis();
        DoThis.Inner inner = doThis.new Inner();
        DoThis.Inner inner1 = doThis.getInner();
    }
}
