package dataStruct.graph.prim;

/**
 * Dijkstra单源最短路径算法
 */
public class Dijkstra {
    public static final int M = 10000; // 代表正无穷

    public static void main(String[] args) {
        // 二维数组每一行分别是 A、B、C、D、E 各点到其余点的距离,
        // A -> A 距离为0, 常量M 为正无穷
        int[][] weight1 = {
                {0, 4, M, 2, M},
                {4, 0, 4, 1, M},
                {M, 4, 0, 1, 3},
                {2, 1, 1, 0, 7},
                {M, M, 3, 7, 0}
        };

        int start = 0;

        int[] shortPath = dijkstra(weight1, start);

        for (int i = 0; i < shortPath.length; i++)
            System.out.println("从" + start + "出发到" + i + "的最短距离为：" + shortPath[i]);
    }

    /**
     * dijkstra算法  核心思想：辅助标记数组标记当前点是否访问过，每个大循环找寻权值最小点
     * 比较   权值最小点+该点到其他点的权值 是否小于原先点的权值 若小于则更新
     *
     * @param weight 邻接矩阵
     * @param start  开始点
     * @return
     */
    public static int[] dijkstra(int[][] weight, int start) {
        int nodeNum = weight.length;
        boolean[] visited = new boolean[nodeNum];
        visited[start] = true;//标记开始节点已被访问
        int[] shortest = new int[nodeNum];
        shortest[start] = 0;//自己到自己为0

        for (int i = 1; i < nodeNum; i++) {
            int min = M;
            int k = 0;//标记最小权值下标
            for (int j = 0; j < weight.length; j++) {
                //找出权值最小点k
                if (!visited[j] && min > weight[start][j]) {
                    min = weight[start][j];
                    k = j;
                }
            }
            shortest[k] = min;
            visited[k] = true;
            //下面的操作是更新
            for (int m = 0; m < nodeNum; m++) {
                if (!visited[m] && weight[start][m] > min + weight[k][m]) {
                    //更新
                    weight[start][m] =  min + weight[k][m];
                }
            }
        }
        return shortest;
    }
}
