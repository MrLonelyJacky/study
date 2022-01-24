package thinkingJava.innerClass.callback;

/**
 * callee2不同于callee1
 * 它里面使用了内部类从而可以回调自己的increment方法
 */
public class Callee2 extends MyIncrement {
    private int i = 0;

    public void increment() {
        super.increment();
        i++;
        System.out.println(i);
    }

    private class Closure implements Incrementable{

        @Override
        public void increment() {
            Callee2.this.increment();
        }

    }

    //私有的内部类要通过方法获取
    Incrementable getCallback(){
        return new Closure();
    }

}
