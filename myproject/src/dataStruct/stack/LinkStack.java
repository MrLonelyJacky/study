package dataStruct.stack;

import dataStruct.danList.Node;

import java.io.File;

/**
 * Created by 15151 on 2019/1/22.
 */
public class LinkStack {
    //栈顶节点
    private Node top;

    public LinkStack() {
        top = new Node();
    }

    public boolean isNull() {
        return top.getNext() == null;
    }

    public boolean push(Node node) {
        if (isNull()) {
            top.setNext(node);
        } else {
            node.setNext(top.getNext());
            top.setNext(node);
        }
        return true;
    }

    public Node pop() {
        if (isNull()) {
            return null;
        } else {
            Node node = top.getNext();
            top.setNext(top.getNext().getNext());
            return node;
        }
    }

    public static void main(String[] args) {
        LinkStack ls = new LinkStack();

        //1状态
        System.out.println("栈是否为空：" + ls.isNull());

        //2操作
        //依次压栈
        ls.push(new Node('a'));
        ls.push(new Node('b'));
        ls.push(new Node('c'));

        //依次弹栈
        System.out.println("弹栈顺序：");
        System.out.println(ls.pop().getData());
        System.out.println(ls.pop().getData());
        System.out.println(ls.pop().getData());
        File file = new File("D:/aaa.txt");
        if (!file.exists()) {
            System.out.println("not exist!");
        }
    }
}
