package thinkingJava.genericParadigm;

import java.util.Iterator;

/**
 * Created by 15151 on 2019/4/1.
 * 这里我想要让fibonacci类可以迭代，而不能修改该类，采用适配器的方式
 */
public class FibonacciAdapter extends Fibonacci implements Iterable<Integer> {
    private int n;

    public FibonacciAdapter(int n) {
        this.n = n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Integer next() {
                n--;
                return FibonacciAdapter.this.next();
            }
        };
    }

    public static void main(String[] args) {
        //本来
        for (int i : new FibonacciAdapter(18)) {
            System.out.println(i);
        }
    }
}
