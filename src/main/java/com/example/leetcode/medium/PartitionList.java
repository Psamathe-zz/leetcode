package com.example.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class PartitionList {
    public static void main(String[] args) {
        ListNode node6 = new ListNode(1);
        ListNode node5 = new ListNode(5,node6);
        ListNode node4 = new ListNode(2,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(4,node3);
        ListNode node1 = new ListNode(1,node2);
        int x = 0;
        PartitionList partitionList = new PartitionList();
        partitionList.partition(node6, x);
    }
    public ListNode partition(ListNode head, int x) {

        Queue<ListNode> lessQueue = new LinkedList<>();
        Queue<ListNode> moreQueue= new LinkedList<>();
        ListNode t = head;
        while (t != null){
            if(t.val < x){
                lessQueue.add(t);
            } else {
                moreQueue.add(t);
            }
            t = t.next;
        }
        head = lessQueue.poll();
        t = head;
        ListNode pre = null;
        while (t != null){
            if(pre != null)
                pre.next = t;
            pre = t;
            t = lessQueue.poll();
        }

        t = moreQueue.poll();
        if(head == null)
            head = t;
        while (t != null){
            if(pre != null)
                pre.next = t;
            pre = t;
            t = moreQueue.poll();
        }

        if(pre != null)
            pre.next = t;
        return head;
    }

    /**
     * fater solution
     * @param head
     * @param x
     * @return
     */
    public ListNode partitionV2(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode curr1 = dummy;
        ListNode curr2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                curr1.next = head;
                curr1 = curr1.next;
            } else {
                curr2.next = head;
                curr2 = curr2.next;
            }
            head = head.next;
        }
        curr2.next =null;
        curr1.next = dummy2.next;
        return dummy.next;
    }


    /**
     * less memory
     * @param head
     * @param x
     * @return
     */
    public ListNode partitionV3(ListNode head, int x) {
        if(null == head){
            return head;
        }

        ListNode dummyLeft = new ListNode(0);
        ListNode dummyRight = new ListNode(0);
        ListNode dummy = dummyLeft;
        ListNode tmp = dummyRight;
        while(null != head){
            int v = head.val;
            if(v >= x){
                dummyRight.next = new ListNode(v);
                dummyRight = dummyRight.next;
            }else{
                dummyLeft.next = new ListNode(v);
                dummyLeft = dummyLeft.next;
            }

            head = head.next;
        }

        dummyLeft.next = tmp.next;

        return dummy.next;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
