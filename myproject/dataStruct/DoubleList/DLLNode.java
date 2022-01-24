package dataStruct.DoubleList;

/**
 * Created by 15151 on 2019/1/14.
 */
//创建一个双向链表结点类，并用get，set方法获取其数据。
public class DLLNode {
    private int data;//数据域
    private DLLNode next;//后继指针域
    private DLLNode previous;//前驱指针域

    public DLLNode(int data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DLLNode getNext() {
        return next;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }

    public DLLNode getPrevious() {
        return previous;
    }

    public void setPrevious(DLLNode previous) {
        this.previous = previous;
    }
    //显示该结点的数据
    public void display() {
        System.out.print( data + " ");
    }
}

