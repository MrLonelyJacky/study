package thinkingJava.innerClass.thisNew;

/**
 * Created by 15151 on 2019/2/17.
 *
 */
public class DoThis {

    public class Inner{
        private DoThis out(){
            return DoThis.this;
        }
    }

    public Inner getInner(){
        return new Inner();
    }

    public void f(){
        System.out.println(".this");
    }

    public static void main(String[] args) {
        DoThis doThis = new DoThis();
        doThis.getInner().out().f();
    }
}
