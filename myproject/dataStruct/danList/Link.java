package dataStruct.danList;

/**
 * Created by 15151 on 2019/1/2.
 * 单链表java实现
 */
public class Link {
    /*Node head;
    Node current;

    public void add(int data.txt) {
        if (head == null) {
            head = new Node(data.txt);
            current = head;
        } else {
            current.next = new Node(data.txt);
            current = current.next;
        }
    }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data.txt+" ");
            curr = curr.next;
        }
        System.out.println();
    }

    public int getLength() {
        if (head == null) {
            return 0;
        }
        int length = 0;
        Node curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    public Node findLastNode(int index) {
        Node first = head;
        Node second = head;
        for (int i = 0; i < index - 1; i++) {
            second=second.next;
            if (second==null){
                return  null;
            }
        }
        while (second.next!=null){
            second=second.next;
            first=first.next;
        }
        return first;
    }

    public Node findMiddleNode(){
        Node first=head;
        Node second=head;
        while (second!=null&&second.next!=null){
            first=first.next;
            second=second.next.next;
        }
        return first;
    }*/
}
