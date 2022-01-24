package thinkingJava.genericParadigm;

/**
 * Created by 15151 on 2019/3/28.
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                '}';
    }

}
