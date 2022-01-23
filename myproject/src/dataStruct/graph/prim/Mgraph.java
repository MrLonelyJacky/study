package dataStruct.graph.prim;

import java.util.*;

/**
 * Created by 15151 on 2019/9/6.
 * 图的邻接矩阵表示prim算法和kruska算法
 */
public class Mgraph {
    //点数组
    public char[] vex;
    //边数组
    public int[][] edges;
    //边数组 含权值
    private List<Edge> edges2;
    //顶点数
    public int vexNum;
    //弧数
    public int arcNum;
    //记录并查集中的数据
    private int[] array;
    private static final int MAX_WEIGHT = 1000;// 代表顶点之间不连通

    public Mgraph(char[] vex, int[][] edges) {
        this.vex = vex;
        this.edges = edges;
        this.vexNum = vex.length;

        //this.arcNum = edges.length;
    }

    /**
     * prim算法最小生成树（必须连通网）
     *
     * @param mgraph
     * @param start
     */
    private int[] prim(Mgraph mgraph, int start) {
        int[] hasNode = new int[mgraph.vexNum];//已存放节点 即最后的最小生成树
        int hasNodeIndex = 0;
        hasNode[hasNodeIndex++] = start;

        List<Integer> difference = new ArrayList<>();
        for (int i = 0; i < mgraph.vexNum; i++) {
            difference.add(mgraph.edges[start][i]);
        }
        difference.set(0, 0);
        for (int i = 0; i < mgraph.vexNum; i++) {
            if (i == start) {
                continue;
            }
            //找寻未被访问过的权值最小的点
            int min = MAX_WEIGHT;
            int k = 0;
            for (int j = 0; j < mgraph.vexNum; j++) {
                if (difference.get(j) != 0 && min > difference.get(j)) {
                    min = difference.get(j);
                    k = j;
                }
            }
            //找到最小点 将其加入最小生成树 并将权值置为0
            hasNode[hasNodeIndex++] = k;
            difference.set(k, 0);
            //这时生成树中的点到了 k  要将权值更新为k点中较小的权值，这样保证了每次未被访问的权值最小即可
            for (int m = 0; m < mgraph.vexNum; m++) {
                if (difference.get(m) != 0 && mgraph.edges[k][m] < difference.get(m)) {
                    //更新
                    difference.set(m, mgraph.edges[k][m]);
                }
            }
        }
        return hasNode;
    }

    //推荐使用这种
    private char[] prim(Mgraph mgraph) {
        char[] hasNode = new char[mgraph.vexNum];//已存放节点 即最后的最小生成树
        int hasNodeIndex = 0;
        hasNode[hasNodeIndex++] = mgraph.vex[0];
        List<Integer> difference = new ArrayList<>();
        for (int i = 0; i < mgraph.vexNum; i++) {
            difference.add(mgraph.edges[0][i]);
        }
        difference.set(0, 0);
        for (int i = 0; i < mgraph.vexNum; i++) {
            if (i == 0) {
                continue;
            }
            //找寻未被访问过的权值最小的点
            int min = MAX_WEIGHT;
            int k = 0;
            for (int j = 0; j < mgraph.vexNum; j++) {
                if (difference.get(j) != 0 && min > difference.get(j)) {
                    min = difference.get(j);
                    k = j;
                }
            }
            //找到最小点 将其加入最小生成树 并将权值置为0
            hasNode[hasNodeIndex++] = mgraph.vex[k];
            difference.set(k, 0);
            //这时生成树中的点到了 k  要将权值更新为k点中较小的权值，这样保证了每次未被访问的权值最小即可
            for (int m = 0; m < mgraph.vexNum; m++) {
                if (difference.get(m) != 0 && mgraph.edges[k][m] < difference.get(m)) {
                    //更新
                    difference.set(m, mgraph.edges[k][m]);
                }
            }
        }
        return hasNode;
    }

    /**
     * 克鲁斯卡尔算法，采用并查集判断是否有回路
     */
    private List<Edge> kruska(Mgraph mgraph) {
        List<Edge> list = new ArrayList<>();
        initUnion();
        mgraph.edges2.sort((o1, o2) -> {
            if (o1.getWeight() > o2.getWeight()) {
                return 1;
            } else {
                return -1;
            }
        });
        int k = 0;
        for (Edge edge : mgraph.edges2) {
            if (k == mgraph.vexNum - 1) {
                break;
            }
            int startParent = find(edge.getStart());
            int endParent = find(edge.getEnd());
            if (startParent != endParent) {//该边没有形成回路
                union(edge.getStart(), find(edge.getEnd()));
                list.add(edge);
                k++;
            }
        }
        return list;
    }

    //初始化并查集
    private void initUnion() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    //并查集并操作
    private void union(int i, int j) {
        if (array[i] > array[j]) {
            array[i] = j;
        } else {
            array[j] = i;
        }
    }

    //并查集查找根
    private int find(int i) {
        int j = i;
        while (array[i] != i) {
            int temp = array[i];
            i = array[temp];
        }
        //压缩路径
        while (array[j] != j) {
            int temp = array[j];
            array[j] = i;
            j = array[temp];
        }
        return i;
    }

    public static void main(String[] args) {
        //图中的顶点
        char[] vex = new char[7];
        vex[0] = 'A';
        vex[1] = 'B';
        vex[2] = 'C';
        vex[3] = 'D';
        vex[4] = 'E';
        vex[5] = 'F';
        vex[6] = 'G';
        //边的邻接矩阵 含权值
        int[] v0 = {0, 12, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 14};
        int[] v1 = {12, 0, 10, MAX_WEIGHT, MAX_WEIGHT, 7, MAX_WEIGHT};
        int[] v2 = {MAX_WEIGHT, 10, 0, 3, 5, 6, MAX_WEIGHT};
        int[] v3 = {MAX_WEIGHT, MAX_WEIGHT, 3, 0, 4, MAX_WEIGHT, MAX_WEIGHT};
        int[] v4 = {MAX_WEIGHT, MAX_WEIGHT, 5, 4, 0, 2, 8};
        int[] v5 = {16, 7, 6, MAX_WEIGHT, 2, 0, 9};
        int[] v6 = {14, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8, 9, 0};
        int[][] edges = new int[7][7];
        edges[0] = v0;
        edges[1] = v1;
        edges[2] = v2;
        edges[3] = v3;
        edges[4] = v4;
        edges[5] = v5;
        edges[6] = v6;
        Mgraph mgraph = new Mgraph(vex, edges);
        //int[] prim = mgraph.prim(mgraph, 0);
        char[] prim = mgraph.prim(mgraph);
        System.out.println(Arrays.toString(prim));

        //测试克鲁斯卡尔
        List<Edge> list = new ArrayList<>();
        Edge edge0 = new Edge(0, 1, 12);
        list.add(edge0);
        Edge edge1 = new Edge(1, 2, 10);
        list.add(edge1);
        Edge edge2 = new Edge(2, 3, 3);
        list.add(edge2);
        Edge edge3 = new Edge(3, 4, 4);
        list.add(edge3);
        Edge edge4 = new Edge(4, 5, 2);
        list.add(edge4);
        Edge edge5 = new Edge(5, 6, 9);
        list.add(edge5);
        Edge edge6 = new Edge(0, 5, 16);
        list.add(edge6);
        Edge edge7 = new Edge(0, 6, 14);
        list.add(edge7);
        Edge edge8 = new Edge(1, 5, 7);
        list.add(edge8);
        Edge edge9 = new Edge(2, 4, 5);
        list.add(edge9);
        Edge edge10 = new Edge(2, 5, 6);
        list.add(edge10);
        Edge edge11 = new Edge(4, 6, 8);
        list.add(edge11);
        mgraph.edges2 = list;
        mgraph.array = new int[mgraph.edges2.size()];
        List<Edge> kruska = mgraph.kruska(mgraph);
        kruska.forEach(item -> {
            System.out.println(item.getStart() + "：" + item.getEnd());
        });
    }

}
