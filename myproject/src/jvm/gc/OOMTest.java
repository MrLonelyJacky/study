package jvm.gc;

/**
 * Created by 15151 on 2019/5/27.
 */
public class OOMTest {
    public static void main(String[] args) {
        for (int i = 0; i < 250000; i++) {
            OOMTest oomTest = new OOMTest();
        }
    }
}
