package dataStruct.Josephu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 15151 on 2019/1/19.
 */
public class Josephus<T> {
    private Queue<T> queue;

    public Josephus(int length) {
        queue = new ArrayDeque<>(length);
    }

    public void add(T t) {
        queue.add(t);
    }

    public void process(int interval) {
        int length = queue.size();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < interval - 1; j++) {
                T t = queue.remove();
                queue.add(t);
            }
            T remove = queue.remove();
            System.out.println(remove);
        }
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        Josephus<Integer> josephus = new Josephus<>(8);
        josephus.add(1);
        josephus.add(2);
        josephus.add(3);
        josephus.add(4);
        josephus.add(5);
        josephus.add(6);
        josephus.add(7);
        josephus.add(8);
        josephus.process(4);
    }
}
