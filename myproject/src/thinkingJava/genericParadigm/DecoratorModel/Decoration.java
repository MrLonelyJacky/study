package thinkingJava.genericParadigm.DecoratorModel;

/**
 * Created by 15151 on 2019/5/6.
 */
public class Decoration {
    public static void main(String[] args) {
        TimeStamped timeStamped = new TimeStamped(new Basic());
        TimeStamped timeStamped2 = new TimeStamped(new SerialNumbered(new Basic()));

    }
}
