package dataStruct.Queue;

import dataStruct.danList.Node;

/**
 * Created by 15151 on 2019/1/29.
 */
public class LinkQueue {
    //头指针
    Node front;
    //尾指针
    Node rear;
    //记录队列的长度
    int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    //添加元素
    public void addQueue(int data) {
        if (size == 0) {
            front = new Node();
            front.setData(data);
            rear = front;
            size++;
        } else {
            Node next = new Node();
            next.setData(data);
            //这块有个主意的地方，一旦rail设置了next属性，
            // 因为front节点与rail节点指向了同一个node节点，持有同一个结点的一个引用，因此front节点next属性也被填充
            rear.setNext(next);
            rear = next;
            size++;
        }
    }

    //出队列
    public Object deleteQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空！");
        } else {
            Node delete = front;
            front = front.getNext();
            size--;
            return delete;
        }

    }
}
