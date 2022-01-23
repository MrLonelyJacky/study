package thinkingJava.Annotation.apt;

/**
 * Created by 15151 on 2019/5/10.
 */
@ExtracatInterface("IMutiplier")
public class MutiPlier {
    public int mutiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++) {
            total = add(total, y);
        }
        return total;
    }

    private int add(int x, int y) {
        return x + y;
    }

}
