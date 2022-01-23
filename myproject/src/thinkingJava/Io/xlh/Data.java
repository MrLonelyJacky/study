package thinkingJava.Io.xlh;

import java.io.Serializable;

/**
 * Created by 15151 on 2019/5/10.
 */
public class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Data{" +
                "n=" + n +
                '}';
    }
}
