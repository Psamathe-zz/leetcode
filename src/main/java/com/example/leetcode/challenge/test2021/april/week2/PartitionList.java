package com.example.leetcode.challenge.test2021.april.week2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 */
public class PartitionList {
    public static void main(String[] args) {
        PartitionList partitionList = new PartitionList();
        ListNode p6 = new ListNode(2);
        ListNode p5 = new ListNode(5,p6);
        ListNode p4 = new ListNode(2,p5);
        ListNode p3 = new ListNode(3,p4);
        ListNode p2 = new ListNode(4,p3);
        ListNode p1 = new ListNode(1,p2);
        partitionList.partition(p1,3);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode p = head;
        ListNode smallPointer = new ListNode();
        ListNode smallHead = smallPointer;
        ListNode bigPointer = new ListNode();
        ListNode bigHead = bigPointer;
        while (p!=null){
            if(p.val < x){
                smallPointer.next = p;
                smallPointer = smallPointer.next;
            } else {
                bigPointer.next = p;
                bigPointer = bigPointer.next;
            }
            p = p.next;
        }
        bigPointer.next = null;
        if(bigHead.next != null)
            smallPointer.next = bigHead.next;
        else
            smallPointer.next = null;
        return smallHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
