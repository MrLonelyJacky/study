package dataStruct.ThreadTree;

/**
 * Created by 15151 on 2019/2/2.
 */
public class ThreadTree {
    private ThreadTreeNodel preNode;

    public ThreadTreeNodel createTree(String[] nodes, int index) {
        if (index > nodes.length) {
            return null;
        }
        ThreadTreeNodel nodel = new ThreadTreeNodel();
        nodel.data = nodes[index];
        nodel.left = createTree(nodes, 2 * index + 1);
        nodel.right = createTree(nodes, 2 * index + 2);
        return nodel;
    }

    /**
     * 线索化二叉树
     *
     * @param root
     */
    public void threadTree(ThreadTreeNodel root) {
        if (root == null) {
            return;
        }
        threadTree(root.left);
        if (root.left == null) {
            root.isLeftThread = true;
            root.left = preNode;
        }
        if (preNode != null && preNode.right != null) {
            preNode.isRightThread = true;
            preNode.right = root;
        }
        preNode = root;
        threadTree(root.right);
    }

    /**
     * 中序遍历线索二叉树，按后继方式，这里不需要递归了，因为线索化了
     */
    public void inThreadTree(ThreadTreeNodel nodel) {
        while (nodel != null && !nodel.isLeftThread) {
            nodel = nodel.left;
        }
        while (nodel != null) {
            System.out.println(nodel.data + " ");
            if (nodel.isRightThread) {
                nodel = nodel.right;
            } else {
                nodel = nodel.right;
                while (nodel != null && !nodel.isLeftThread) {
                    nodel = nodel.left;
                }
            }
        }

    }
}

