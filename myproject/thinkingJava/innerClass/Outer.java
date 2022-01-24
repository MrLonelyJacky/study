package thinkingJava.innerClass;

/**
 * Created by 15151 on 2019/2/15.
 * 演示如何带哦用内部类，
 * 内部类可以访问一切外部类的变量，外部类也可以访问内部类的私有元素
 */
public class Outer {
    private String song;

    private void sing(){
        System.out.println("sing a "+song);
    }

    public Outer(String song) {
        this.song = song;
    }

    class Inner{
        private Integer a;
        Inner(){

        }
        private void changeOut(){
            song = "change";
            sing();
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "song=" + song +
                    '}';
        }
    }
    //这里不能用static方法创建内部类
    public  Inner getInner(){
        return new Inner();
    }

    public static void main(String[] args) {
        
        Outer outer = new Outer("jacky");
        Inner inner = outer.getInner();
        inner.changeOut();
        System.out.println(inner);
        outer.create();
    }
    public void create(){
        Inner inner =  new Outer("").getInner();
        Outer.Inner inner1 = new Inner();
        System.out.println(inner.a);
    }
}
