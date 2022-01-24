package thinkingJava.PolyMore;

/**
 * Created by 15151 on 2019/3/4.
 */
public class Base {
    public int i ;
    public  void foo(Base base){
        //由于hahq是可重写的方法，所以是动态绑定
        base.haha();
        //如果是成员变量则是静态绑定
        System.out.println("base"+base.i);
    }
    public  void foo(BaseSon son){
        System.out.println("base son");
    }
    public void haha(){
        System.out.println("Base haha");
    }

}
