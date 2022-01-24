package dataStruct.graph.prim;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 15151 on 2019/9/11.
 * 有向无环图的拓扑排序算法
 */
public class TopoLogical {
    private static int[] indegree;//顶点入度数组

    public static List<VNode> topologicalSort(AdjacencyList adjacencyList) {
        List<VNode> result = new ArrayList<>();
        Stack<VNode> stack = new Stack<>();
        //先入度为0的顶点放入栈中
        for (int i = 0; i < adjacencyList.vexNum; i++) {
            if (indegree[i] == 0) {
                stack.push(adjacencyList.adjList[i]);
            }
        }

        while (!stack.isEmpty()) {
            VNode pop = stack.pop();
            result.add(pop);
            ArcNode first = pop.first;
            while (first != null) {
                //将所有该点邻接点的入度减去1 并且判断该店邻接点的入度是否为0 为0加入栈中
                int adjVex = first.adjVex;
                if ((--indegree[adjVex]) == 0) {
                    stack.push(adjacencyList.adjList[adjVex]);
                }
                first = first.next;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        VNode[] vNodes = new VNode[6];
        VNode vNode1 = new VNode();
        vNode1.nodeData = 1;
        ArcNode arcNode10 = new ArcNode();
        arcNode10.adjVex = 2;
        ArcNode arcNode11 = new ArcNode();
        arcNode11.adjVex = 4;
        arcNode10.next = arcNode11;
        vNode1.first = arcNode10;
        vNodes[1] = vNode1;

        VNode vNode2 = new VNode();
        vNode2.nodeData = 2;
        ArcNode arcNode20 = new ArcNode();
        arcNode20.adjVex = 3;
        ArcNode arcNode21 = new ArcNode();
        arcNode21.adjVex = 4;
        arcNode20.next = arcNode21;
        vNode2.first = arcNode20;
        vNodes[2] =vNode2;

        VNode vNode3 = new VNode();
        vNode3.nodeData = 3;
        ArcNode arcNode30 = new ArcNode();
        arcNode30.adjVex = 5;
        vNode3.first = arcNode30;
        vNodes[3] =vNode3;

        VNode vNode4 = new VNode();
        vNode4.nodeData = 4;
        ArcNode arcNode40 = new ArcNode();
        arcNode40.adjVex = 3;
        ArcNode arcNode41 = new ArcNode();
        arcNode41.adjVex = 5;
        arcNode40.next = arcNode41;
        vNode4.first = arcNode40;
        vNodes[4] =vNode4;

        VNode vNode5 = new VNode();
        vNode5.nodeData = 5;
        vNodes[5] = vNode5;

        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.adjList = vNodes;
        adjacencyList.vexNum = 5;

        indegree = new int[6];
        indegree[0] = -1;
        indegree[1] = 0;
        indegree[2] = 1;
        indegree[3] = 2;
        indegree[4] = 2;
        indegree[5] = 2;

        List<VNode> nodeList = topologicalSort(adjacencyList);
        nodeList.forEach(item -> System.out.println(item.nodeData));
    }
}
