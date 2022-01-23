package dataStruct.Tree;

/**
 * Created by 15151 on 2019/1/31.
 */
class Node {
    String data;    //数据域
    Node left;      //左指针域
    Node right;     //右指针域
    byte leftType;  //左指针域类型 0：指向子节点、1：前驱或后继线索
    byte rightType; //右指针域类型 0：指向子节点、1：前驱或后继线索

    public static void main(String[] args) {

    }

    static int count = 0;

    public static int haha(Node node) {
        if (node.left == null && node.right == null) {
            //递归到叶子节点结束
            return count;
        }
        if (node.left != null && node.right != null) {
            count++;
        }
        haha(node.left);
        haha(node.right);
        return count;
    }
}
