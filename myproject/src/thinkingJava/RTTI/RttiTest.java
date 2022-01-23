package thinkingJava.RTTI;

/**
 * Created by 15151 on 2019/4/19.
 */
public class RttiTest {
    public static void main(String[] args) {
        new Candy();
        //new Gun();
        try {
            Class<?> aClass = Class.forName("thinkingJava.RTTI.Gun");
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
