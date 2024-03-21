package com.example.leetcode.challenge.test2024.mars.week4;

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

    }

    public ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode pNext;
        ListNode x = null;

        while ( p != null) {
            pNext = p.next;
            p.next = x;
            x = p;
            p = pNext;
        }
        return x;
    }
}
