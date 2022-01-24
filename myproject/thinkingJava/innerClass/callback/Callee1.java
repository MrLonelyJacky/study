package thinkingJava.innerClass.callback;

/**
 * callee1 简单直接实现Incrementable 接口
 */
public class Callee1 implements Incrementable {
    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}
