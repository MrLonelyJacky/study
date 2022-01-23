package dataStruct.DoubleList;

import dataStruct.danList.Node;

/**
 * Created by 15151 on 2019/1/14.
 *
 */
public class DoubleList {
    private DLLNode first;
    private DLLNode last;

    public DoubleList() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    //头插
    public void addFirst(int value) {
        DLLNode newNode = new DLLNode(value);
        if (isEmpty()) {
            last = newNode;

        } else {
            first.setPrevious(newNode);
        }
        newNode.setNext(first);
        first = newNode;
    }

    //尾插
    public void addLast(int value) {
        DLLNode newNode = new DLLNode(value);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
        }
        last = newNode;
    }

    //插入节点  如果gai'zhi
    public void add(int value, int newValue) throws Exception {
        //如果链表为空头插或尾插进行一次插入
        if (isEmpty()) {
            addFirst(value);
            return;
        }
        //不为空则开始找到该节点
        DLLNode curr = first;
        while (value != curr.getData()) {
            curr = curr.getNext();
            //如果找到尾部还没找到则表示不存在该节点
            if (curr == null) {
                throw new Exception("没有该节点！");
            }
        }
        //程序走到这说明当前节点就是要找的系欸但
        //如果是尾节点
        DLLNode newNode = new DLLNode(newValue);
        if (curr == last) {
            newNode.setNext(null);
            newNode.setPrevious(curr);
            curr.setNext(newNode);
            last = newNode;
        } else {
            curr.getNext().setPrevious(newNode);
            curr.setNext(newNode);
            newNode.setNext(curr.getNext());
            newNode.setPrevious(curr);
        }

    }

    public static void main(String[] args) {
        String t = "####";
        System.out.println(t.length());
    }
}
