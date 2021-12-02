package com.example.leetcode.challenge.test2021.december;

import com.example.leetcode.model.ListNode;

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * Example 2:
 *
 *
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 */
public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode p5 = new ListNode(5);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);
        OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
        oddEvenLinkedList.oddEvenList(p1);
    }

    public ListNode oddEvenList(ListNode head) {
        int index = 0;
        ListNode oddHead = new ListNode();
        ListNode evenHead = new ListNode();
        ListNode p = head;
        ListNode oddP = oddHead;
        ListNode evenP = evenHead;
        while (p != null) {
            if(index % 2 == 0){
                oddP.next = p;
                oddP = oddP.next;
            } else {
                evenP.next = p;
                evenP = evenP.next;
            }
            index++;
            p = p.next;
        }
        oddP.next = evenHead.next;
        evenP.next = null;
        return oddHead.next;
    }
}
