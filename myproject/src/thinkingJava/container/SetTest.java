package thinkingJava.container;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 15151 on 2019/3/27.
 */
public class SetTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.poll();
        System.out.println(queue);
    }
}
