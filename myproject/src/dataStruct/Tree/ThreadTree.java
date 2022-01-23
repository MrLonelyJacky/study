package dataStruct.Tree;

/**
 * Created by 15151 on 2019/1/30.
 */
public class ThreadTree {
    /**
     * 该节点记录上次节点的位置
     */
    private BinNode preNode;

    /**
     * 创建二叉树，为了方便操作，暂且使用完全二叉树
     */
    public BinNode createTree(int[] data, int index) {
        if (index >= data.length) {
            return null;
        }
        BinNode node = new BinNode();
        node.setData(data[index]);
        node.setLeft(createTree(data, 2 * index + 1));
        node.setRight(createTree(data, 2 * index + 2));
        return node;
    }

    /**
     * 以root根节点线索话二叉树
     * 这个和终须遍历很像
     */
    public void inThread(BinNode root) {
        inThread(root.getLeft());

        if (root.getLeft() == null) {
            root.setLeftThread(true);

        }
        inThread(root.getRight());
    }

}
