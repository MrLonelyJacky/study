package thinkingJava.chapter12;

/**
 * Created by 15151 on 2020/3/19.
 * u can define exception u need  but mind if the exception java has
 * we do not need define like IllegaArgumentException
 * if u define exception u should  extend a java exception that approach your exception meaning
 *
 */
public class SimpleException extends Exception{
    public SimpleException() {
    }

    public SimpleException(String message) {
        super(message);
    }
}
