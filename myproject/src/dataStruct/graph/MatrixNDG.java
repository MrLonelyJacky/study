package dataStruct.graph;

/**
 * Created by 15151 on 2019/5/23.
 * 邻接矩阵表示无相图
 */
public class MatrixNDG {
    int size;//图的顶点个数
    char[] vertexs;//图的定点名臣
    int[][] matrix;//图的邻接矩阵

    public MatrixNDG(char[] vertexs, char[][] edges) {
        this.size = vertexs.length;
        matrix = new int[this.size][this.size];
        this.vertexs = vertexs;
        for (char[] c : edges) {
            int position1 = getPosition(c[0]);
            int position2 = getPosition(c[1]);
            matrix[position1][position2] = 1;
            matrix[position2][position1] = 1;
        }
    }

    private int getPosition(char i) {
        for (int j = 0; j < vertexs.length; j++) {
            if (i == vertexs[j]) {
                return j;
            }
        }
        return -1;
    }

    private void print() {
        for (int[] a : matrix) {
            for (int i : a) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'D', 'G'},
                {'I', 'J'},
                {'J', 'G'},};
        MatrixNDG pG;
        // 自定义"图"(输入矩阵队列)
        // 采用已有的"图"
        long start = System.nanoTime();
        pG = new MatrixNDG(vexs, edges);
        pG.print();   // 打印图
        long end = System.nanoTime();
        System.out.println(end - start);
    }
}
