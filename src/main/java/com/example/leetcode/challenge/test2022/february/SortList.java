package com.example.leetcode.challenge.test2022.february;

import com.example.leetcode.model.ListNode;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 */
public class SortList {
    public static void main(String[] args) {

    }

    public ListNode sortList(ListNode head) {
        ListNode p1 = head;
        ListNode p2;
        int temp;
        while (p1 != null) {
            p2 = head;
            while (p2 != p1) {
                if(p1.val < p2.val) {
                    temp = p1.val;
                    p1.val = p2.val;
                    p2.val = temp;
                }
                p2 = p2.next;
            }
            p1 = p1.next;
        }

        return head;
    }
}
