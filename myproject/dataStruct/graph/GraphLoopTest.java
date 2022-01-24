package dataStruct.graph;

import java.util.*;

/**
 * Created by 15151 on 2019/5/24.
 */
public class GraphLoopTest {
    private Map<String, List<String>> graph = new HashMap<>();

    private void initData() {
        //        图结构如下
        //          1
        //        /   \
        //       2     3
        //      / \   / \
        //     4  5  6  7
        //      \ | / \ /
        //        8    9
        graph.put("1", Arrays.asList("2", "3"));
        graph.put("2", Arrays.asList("1", "4", "5"));
        graph.put("3", Arrays.asList("1", "6", "7"));
        graph.put("4", Arrays.asList("2", "8"));
        graph.put("5", Arrays.asList("2", "8"));
        graph.put("6", Arrays.asList("3", "8", "9"));
        graph.put("7", Arrays.asList("3", "9"));
        graph.put("8", Arrays.asList("4", "5", "6"));
        graph.put("9", Arrays.asList("6", "7"));
        status.put("1", true);
        //queue.offer("1");
        //stackAndQueue.push("1");
    }

    /**
     * 宽度优先搜索(BFS, Breadth First Search)
     * BFS使用队列(queue)来实施算法过程
     */
    private Queue<String> queue = new LinkedList<>();

    //记录是否遍历
    private Map<String, Boolean> status = new HashMap<>();

    private void bfsLoop() {
        String poll = queue.poll();
        status.put(poll, true);
        System.out.print(poll + " ");
        List<String> list = graph.get(poll);
        for (String s : list) {
            if (!status.getOrDefault(s, false)) {
                //如果未被遍历
                queue.add(s);
                status.put(s, true);
            }
        }
        if (!queue.isEmpty()) {
            bfsLoop();
        }
    }

    private Stack<String> stack = new Stack<>();

    private void dfsLoop(String start) {

        //查看栈顶元素，但并不出栈
        System.out.println(start);
        //  2) 找出与此点邻接的且尚未遍历的点，进行标记，然后全部放入queue中。
        List<String> neighborPoints = graph.get(start);
        for (String point : neighborPoints) {
            if (!status.getOrDefault(point, false)) { //未被遍历
                //stackAndQueue.push(point);
                status.put(point, true);
                dfsLoop(point);
            }
        }

    }

    public static void main(String[] args) {
        GraphLoopTest graphLoopTest = new GraphLoopTest();
        graphLoopTest.initData();
        //graphLoopTest.bfsLoop();
        System.out.println();
        graphLoopTest.dfsLoop("1");

    }

}
