package thinkingJava.RTTI.zcFactory;

/**
 * Created by 15151 on 2019/4/23.
 */
public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}
