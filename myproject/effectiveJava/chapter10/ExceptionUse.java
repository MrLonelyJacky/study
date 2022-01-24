package effectiveJava.chapter10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2020/3/16.
 * exception is just used on exception
 * do not use exception to control process
 * a good api should not force the client users to use exception control process
 */
public class ExceptionUse {
    public static void main(String[] args) {
        doSome();
        try {
            doSome2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * horrible abuse use of exception
     * the loop based on exception can reduce performance  try{loop....} reduces performance
     * if we call a method in the try catch ,but the method produces an exception
     * that is confused
     * we can provide a status test method like iterator.hasNext
     *
     * @param arrays
     */
    public void abuseException(int[] arrays) {
        try {
            int i = 0;
            while (true) {
                int c = arrays[i++];
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    /**
     *
     * @throws ArithmeticException
     */
    public static void doSome() throws ArithmeticException {

    }

    public static void doSome2() throws IOException {
        List list = new ArrayList();
        list.iterator();
    }
}
