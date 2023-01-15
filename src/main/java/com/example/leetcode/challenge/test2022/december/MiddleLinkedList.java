package com.example.leetcode.challenge.test2022.december;


import com.example.leetcode.model.ListNode;

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 */
public class MiddleLinkedList {
    public static void main(String[] args) {

    }

    public ListNode middleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        while (p2 != null) {
            p2 = p2.next;
            if(p2 != null) {
                p2 = p2.next;
                p1 = p1.next;
            }
        }

        return p1;

    }
}
