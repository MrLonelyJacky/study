package thinkingJava.container;

import java.util.*;

/**
 * Created by 15151 on 2019/3/27.
 */
public class ProQUeue {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Random random = new Random(9);
        for (int i=0;i<9;i++){
            priorityQueue.offer(random.nextInt(i+10));
        }
        System.out.println(priorityQueue);
        priorityQueue = new PriorityQueue<>();
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8);
        priorityQueue.addAll(list);
        System.out.println(priorityQueue);
        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.asList(1,3,3,2,5,6,7,3,4));
        priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(set);
        System.out.println(priorityQueue);
    }
}
