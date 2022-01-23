package dataStruct.graph;

/**
 * Created by 15151 on 2019/5/23.
 * 图的邻接表表示法
 */
public class ListNDG {
    Vertex[] vertices;//邻接表数组
    int size;//节点个数

    class Vertex {
        //简单的链表节点类
        char ch;
        Vertex next;

        Vertex(char ch) {
            this.ch = ch;
        }

        //添加到尾部
        void add(char ch) {
            Vertex vertex = this;
            while (vertex.next != null) {
                vertex = vertex.next;
            }
            vertex.next = new Vertex(ch);
        }
    }

    public ListNDG(char[] vertex, char[][] edges) {
        size = vertex.length;
        this.vertices = new Vertex[size];//确定邻接表数组的大小
        //初始化头节点
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(vertex[i]);
        }
        //存储边信息
        for (char[] c : edges) {
            //获取位置
            int position1 = getPosition(c[0]);
            //加入节点
            vertices[position1].add(c[1]);
            int position2 = getPosition(c[1]);
            vertices[position2].add(c[0]);
        }
    }

    private int getPosition(char i) {
        for (int j = 0; j < size; j++) {
            if (i == vertices[j].ch) {
                return j;
            }
        }
        return -1;
    }

    //遍历输出邻接表
    public void print() {
        for (int i = 0; i < size; i++) {
            Vertex temp = vertices[i];
            while (temp != null) {
                System.out.print(temp.ch + " ");
                temp = temp.next;
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

        ListNDG pG;

        long start = System.nanoTime();

        pG = new ListNDG(vexs, edges);
        pG.print();   // 打印图


        long end = System.nanoTime();

        System.out.println(end - start);

    }
}
