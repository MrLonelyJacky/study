package thinkingJava.innerClass.callback;

/**
 * Created by 15151 on 2019/4/9.
 */
public class Caller {
    private Incrementable callback;

    public Caller(Incrementable callback) {
        this.callback = callback;
    }

    void go(){
        callback.increment();
    }
}
