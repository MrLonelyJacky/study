package thinkingJava.RTTI.checkCast;

/**
 * Created by 15151 on 2019/4/21.
 */
public class CountedInteger {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "CountedInteger{" +
                "id=" + id +
                '}';
    }

    public static void main(String[] args) {

    }
}
