package dataStruct;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {

    public static class ListNode {
        int val;
        ListNode next = null;
    }

    public ListNode addInList(ListNode head1, ListNode head2) {
        // write code here
        ListNode r1 = reverse(head1);
        ListNode r2 = reverse(head2);
        boolean isLargeTen = false;



        ListNode head = new ListNode();

        while (r1 != null || r2 != null) {
            int val;
            if (isLargeTen) {
                if (r1 == null) {
                    val = r2.val + 1;
                } else if (r2 == null) {
                    val = r1.val + 1;
                } else {
                    val = r1.val + r2.val + 1;
                }

            } else {
                if (r1 == null) {
                    val = r2.val;
                } else if (r2 == null) {
                    val = r1.val;
                } else {
                    val = r1.val + r2.val;
                }
            }


            if (val >= 10) {
                val = val - 10;
                isLargeTen = true;
            } else {
                isLargeTen = false;
            }
            if (r1 != null) {
                r1 = r1.next;
            }
            if (r2 != null) {
                r2 = r2.next;
            }
            ListNode resultNext = new ListNode();
            resultNext.val = val;
            ListNode headNext = head.next;
            head.next = resultNext;
            resultNext.next = headNext;

        }
        if (isLargeTen){
            ListNode lastNode = new ListNode();
            lastNode.val = 1;
            ListNode headNext = head.next;
            head.next =lastNode;
            lastNode.next = headNext;
        }
        return head.next;
    }


    public ListNode reverse(ListNode head1) {
        //初始化一个头节点
        ListNode head = new ListNode();

        while (head1 != null) {
            ListNode next = head.next;
            ListNode newNode = new ListNode();
            head.next = newNode;
            newNode.val = head1.val;
            newNode.next = next;
            head1 = head1.next;
        }
            //删除空的头节点
            head = head.next;
        return head;
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        ListNode head1 = new ListNode();
        head1.val = 9;
        ListNode head2 = new ListNode();
        head2.val = 3;
        ListNode head3 = new ListNode();
        head3.val = 7;
        head1.next = head2;
        head2.next = head3;

        ListNode newHead1 = new ListNode();
        newHead1.val = 6;
        ListNode newHead2 = new ListNode();
        newHead2.val = 3;
        newHead1.next = newHead2;
        Solution solution = new Solution();
        ListNode listNode = solution.addInList(head1, newHead1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
