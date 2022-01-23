package thinkingJava.jiekou;

/**
 * Created by 15151 on 2019/3/30.
 */
public abstract class Instrument {
    private int i=1;
    public abstract void play();
    public String what(){
        return "instrument";
    }

    public static void main(String[] args) {
        //这其实并不是实例化抽象类   而是创建抽象类的一个匿名继承实例
        Instrument instrument = new Instrument() {
            @Override
            public void play() {
                System.out.println("aa");
            }
        };
        System.out.println(instrument.i);
    }
}
