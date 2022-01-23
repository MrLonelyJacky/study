package dataStruct.list;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by 15151 on 2019/6/21.
 */
public class SingleLinkList {
    private Node head = new Node();//头节点

    private Node tail;//尾指针

    public static class Node {
        Integer data;//数据域
        Node next;//指针域
    }

    //头插法
    public void addHead(Node node) {
        if (head.next == null) {
            head.next = node;
        } else {
            node.next = head.next;
            head.next = node;
        }
    }

    public void addTail(Node node) {
        if (tail == null) {
            tail = node;
            //头节点初始化 遍历时需要
            head.next = node;
        } else {
            tail.next = node;
            tail = node;
        }

    }

    public void print() {
        if (head.next == null) {
            System.out.println("empty list");
            return;
        }
        Node now = head.next;
        while (now != null) {
            System.out.print(now.data + " ");
            now = now.next;
        }
        System.out.println();
    }

    //指定位置插入节点
    private void insertNode(int index, Node node) {
        Node prev = searchIndex(index - 1);
        if (prev != null && prev.next != null) {
            node.next = prev.next;
            prev.next = node;
        }
        if (prev != null && prev.next == null) {
            prev.next = node;
        }
        if (prev == null) {
            tail.next = node;
            tail = node;
        }
    }

    //根据位置查找 不包括头节点
    public Node searchIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("参数不合法");
        }

        int count = 0;
        Node now = head.next;
        Node curr = null;
        while (count <= index) {
            if (now != null) {
                curr = now;
                now = now.next;
            }
            count++;
        }
        return curr;
    }

    //递归删除节点值为x的节点 假设没有头节点
    //这种情况下不能使用原先的打印方法，因为原先打印方法是从头指针开始
    public void remove(Node node, Object x, Node prev) {
        if (node == null) {
            return;
        }
        if (x.equals(node.data)) {
            if (prev == null) {
                //第一个节点
                Node next = node.next;

                node = null;
                remove(next, x, null);
            } else {
                Node next = node.next;
                prev.next = next;
                //这里有必要说明一下 对于java节点node没有被引用也没有被引用，
                node = null;
                remove(next, x, prev);
            }
        } else {
            remove(node.next, x, node);
        }
    }

    //只带头节点的链表逆序输出 通过栈的方式
    public void reverse() {
        Node curr = head.next;
        Stack<Node> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.data + " ");
        }
    }

    //删除最小值节点
    //java没有指针 所以实现起来复杂些，用c比较好
    public void removeMin(Node head) {
        Node curr = head.next;
        Node min = head.next;
        Node prev = head;
        Node currPrv = head;

        while (curr != null) {
            if (min.data > curr.data) {
                min = curr;
                currPrv =prev;
            }
            prev = curr;
            curr = curr.next;
        }
        currPrv.next = min.next;
    }

    public static void main(String[] args) {

        SingleLinkList singleLinkList = new SingleLinkList();
        for (int i = 0; i < 5; i++) {
            Node node = new Node();
            node.data = i;

            singleLinkList.addHead(node);
        }

        //singleLinkList.remove(singleLinkList.head.next,2,null);
        singleLinkList.print();
        singleLinkList.reverse();
        /*SingleLinkList singleLinkList2 = new SingleLinkList();
        for (int i = 0; i < 5; i++) {
            Node node = new Node();
            node.data.txt = i;
            singleLinkList2.addTail(node);
        }
        singleLinkList2.print();
        System.out.println(singleLinkList2.searchIndex(0).data.txt);*/

    }
}
