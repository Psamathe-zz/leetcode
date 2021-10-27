package com.example.leetcode.challenge.test2021.september.week1;

import com.example.leetcode.model.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode p5 = new ListNode(5);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        reverseLinkedList.reverseList(p1);
    }

    public ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode();
        ListNode p = newHead;
        ListNode temp;
        while (head != null){
            temp = p.next;
            p.next = head;
            head = head.next;
            p.next.next = temp;

        }
        return newHead.next;
    }
}
