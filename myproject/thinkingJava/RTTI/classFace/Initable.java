package thinkingJava.RTTI.classFace;

/**
 * Created by 15151 on 2019/4/19.
 */
public class Initable {
    static final int staticFianl = 47;
    static final int staticFianl2 = ClassInitalization.random.nextInt();
    static {
        System.out.println("init initable");
    }

}
