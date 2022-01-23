package thinkingJava.PolyMore;

/**
 * Created by 15151 on 2019/3/4.
 */
public class BaseSon extends Base{
    @Override
    public void haha(){
        System.out.println("base son haha");
    }
    public static void main(String[] args) {
        Base base =new Base();
        Base baseSon = new BaseSon();
        baseSon.i=3;
        base.foo(baseSon);
    }
}
