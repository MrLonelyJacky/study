package dataStruct.Tree;

/**
 * Created by 15151 on 2019/1/30.
 */
public class BinNode {
    //值
    private int data;
    //左孩子
    private BinNode left;
    //有孩子
    private BinNode right;
    //是否是左线索，即前去
    private boolean isLeftThread;
    //是否是右线索，即后继
    private boolean isRightThread;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinNode getLeft() {
        return left;
    }

    public void setLeft(BinNode left) {
        this.left = left;
    }

    public BinNode getRight() {
        return right;
    }

    public void setRight(BinNode right) {
        this.right = right;
    }

    public boolean isLeftThread() {
        return isLeftThread;
    }

    public void setLeftThread(boolean leftThread) {
        isLeftThread = leftThread;
    }

    public boolean isRightThread() {
        return isRightThread;
    }

    public void setRightThread(boolean rightThread) {
        isRightThread = rightThread;
    }
}
