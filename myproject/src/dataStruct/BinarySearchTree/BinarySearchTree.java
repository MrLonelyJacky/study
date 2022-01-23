package dataStruct.BinarySearchTree;

/**
 * Created by 15151 on 2019/2/14.
 * 二叉查找树
 */
public class BinarySearchTree {
    BSTNode root;

    /**
     * 插入元素
     *
     * @param data
     */
    public void insert(int data) {
        BSTNode newNode = new BSTNode(data);
        if (root == null) {
            root = newNode;
            return;
        }
        BSTNode currentNode = root;
        //这里的parent节点的意义何在

        BSTNode parent;//这个父节点的意义何在？

        while (true) {
            parent = currentNode;
            if (newNode.data > currentNode.data) {
                currentNode = currentNode.right;
                if (currentNode == null) {
                    parent.right = newNode;
                    return;
                }
            } else if (newNode.data == currentNode.data) {
                return;
            } else {
                currentNode = currentNode.left;
                if (currentNode == null) {
                    parent.left = newNode;
                    return;
                }
            }
        }
    }

    /**
     * 获取节点的直接后继节点，并删除后继节点
     *
     * @param bstNode bstNode
     * @return
     */
    public BSTNode getDirectPostNode(BSTNode bstNode) {
        if (bstNode != null) {
            //当前节点指向有孩子
            BSTNode current = bstNode.right;
            BSTNode directNode = bstNode;//后继节点
            BSTNode parent = bstNode;//后继节点的父节点
            while (current != null) {
                parent = directNode;
                directNode = current;
                current = current.left;
            }
            if (bstNode.right != directNode) {
                //如果后继节点
                //
                // 不是删除节点的右孩子
                parent.left = directNode.right;
            }else {
                bstNode.right = null;
            }
            return directNode;
        }
        return null;
    }

    /**
     * 算法的核心，删除节点操作
     * 1.节点是叶子节点 直接删除
     * 2.节点有左孩子或者右孩子  将左孩子或者右孩子直接替换被删除节点
     * 3.节点既有左孩子 也有有孩子 将节点的直接后继替换被删除节点
     *
     * @return
     */
    public boolean delete(int key) {
        boolean isLeftChild = false;
        //开始查找节点看是否存在该节点
        BSTNode current = root;
        BSTNode parent = null;
        //除了找到的第一个节点为根节点比较特殊，parent 为null
        while (current != null && current.data != key) {
            parent = current;
            if (key > current.data) {
                current = current.right;
                isLeftChild = false;
            } else if (key < current.data) {
                current = current.left;
                isLeftChild = true;
            }
        }
        if (current == null) {
            return false;
        }
        if (current.left == null && current.right == null) {
            //节点为叶子节点
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.left == null) {
            //只有有孩子
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.right == null) {
            //只有左孩子
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else {
            //既有左孩子，又有有孩子
            BSTNode directPostNode = getDirectPostNode(current);//找到该节点的后继节点
            //直接把数据里的key值替换即可
            current.data = directPostNode.data;
        }
        return true;
    }

    /**
     * 根据key值查找节点
     *
     * @param key
     */
    public BSTNode findNode(int key) {
        BSTNode current = root;
        while (current != null) {
            if (key > current.data) {
                current = current.right;
            } else if (key < current.data) {
                current = current.left;
            } else {
                return current;
            }
        }
        return null;
    }

    /**
     * 遍历元素 这里采用中序遍历
     */
    public void inOrder(BSTNode bstNode) {
        if (bstNode == null) {
            return;
        }
        inOrder(bstNode.left);
        System.out.print(bstNode.data + " ");
        inOrder(bstNode.right);
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(1);
        tree.insert(3);
        tree.insert(4);
        tree.insert(2);
        tree.insert(7);
        tree.insert(9);
        tree.insert(8);
        //System.out.println(tree.root.data.txt);
        tree.inOrder(tree.root);
        System.out.println();
        System.out.println("-------开始执行删除后-------");
        //System.out.println(tree.getDirectPostNode(tree.root.right).data.txt);
        //System.out.println(tree.findNode(7).data.txt);
        tree.delete(3);
        tree.inOrder(tree.root);
    }


}
