package dataStruct.graph.prim;

/**
 * 　            ┏┓　　　   ┏┓+ +
 * 　　　　　　　┏┛┻━━━━━━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃━　　　━　　 ┃ ++ + + +
 * 　　　　　　 ████━████    ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * 多源最短路径算法
 */
public class Floyed {
    //    表示无穷大 即不可达
    public static int MAX = Integer.MAX_VALUE;
    //    距离矩阵
    public int[][] dist;
    //    路径Path矩阵
    public int[][] path;

    /**
     * 按点初始化
     *
     * @param size
     */
    public Floyed(int size) {
        this.path = new int[size][size];
        this.dist = new int[size][size];
    }

    public static void print(int[][] arrs) {
        System.out.println("------------------------");
        for (int[] arr : arrs) {
            for (int a : arr) {
                if (a == Floyed.MAX) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(a + " ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    /**
     * 核心算法
     * 构建距离矩阵和路径矩阵
     *
     * @param matrix
     */
    public void floyd(int[][] matrix) {
//        matrix和path length不一致可处理异常
        int size = matrix.length;
        //初始化 dist 和 path
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path[i][j] = -1;
                dist[i][j] = matrix[i][j];
            }
        }
//        核心算法
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
//                  判断如果 ik距离可达且 kj距离可达 且 i和j的距离是否大于 i-> k 与 k->j的距离和
                    if (dist[i][k] != MAX && dist[k][j] != MAX && dist[i][j] > (dist[i][k] + dist[k][j])) {
                        path[i][j] = k;
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    //    查找i到j的路径
    public void findPath(int i, int j) {
//        i = j    0
//        i < size && j < size
        StringBuffer pathStr = new StringBuffer(i + " -> ");
//        List list = new ArrayList(); 也可以存储list里
        tofind(i, j, pathStr);
        pathStr.append(j);
        System.out.println(i + "到" + j + ":");
        System.out.println("最终路径：" + pathStr.toString());
        System.out.println("最终距离：" + dist[i][j]);
    }

    public void tofind(int i, int j, StringBuffer pathStr) {
        if (path[i][j] != -1) {
            pathStr.append(path[i][j] + " -> ");
//           list.add(path[i][j])
            tofind(path[i][j], j, pathStr);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 5, MAX, 7},
                {MAX, 0, 4, 2},
                {3, 3, 0, 2},
                {MAX, MAX, 1, 0}
        };
        Floyed.print(matrix);
        Floyed demo = new Floyed(4);
        demo.floyd(matrix);
        Floyed.print(demo.dist);
        Floyed.print(demo.path);
        demo.findPath(1, 0);
        demo.findPath(2, 0);
    }
}
