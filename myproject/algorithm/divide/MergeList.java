package algorithm.divide;

import dataStruct.Solution;

/**
 * @description:23. 合并 K 个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * @author: jacky
 * @create: 2023-04-11 17:08
 **/
public class MergeList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        return mergeKLists(lists, 0, lists.length - 1);

    }

    public ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            ListNode leftListNode = mergeKLists(lists, left, mid);
            ListNode rightListNode = mergeKLists(lists, mid + 1, right);
            ListNode listNode = null;
            while (leftListNode != null && rightListNode != null) {
                if (listNode == null) {
                    listNode = new ListNode(leftListNode.val);
                }
                if (leftListNode.val < rightListNode.val) {
                    listNode.next = new ListNode(leftListNode.val);
                    leftListNode = leftListNode.next;
                } else {
                    listNode.next = new ListNode(rightListNode.val);
                    rightListNode = rightListNode.next;
                }
            }
            return listNode;
        } else {
            return lists[0];
        }


    }

}
