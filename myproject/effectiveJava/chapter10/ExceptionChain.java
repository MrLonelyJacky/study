package effectiveJava.chapter10;

/**
 * Created by 15151 on 2020/3/19.
 * the exception chain use
 * the exception stack
 */
public class ExceptionChain {
    public void f() throws MyException2{
        try {
            g();
        } catch (MyException1 myException1) {
            myException1.printStackTrace();
            throw new MyException2(myException1);
        }
    }
    public void g() throws MyException1{
        throw new MyException1();
    }

    public static void main(String[] args) {
        ExceptionChain exceptionChain = new ExceptionChain();
        try {
            exceptionChain.f();
        } catch (MyException2 myException2) {
            myException2.printStackTrace();
        }
    }
}
