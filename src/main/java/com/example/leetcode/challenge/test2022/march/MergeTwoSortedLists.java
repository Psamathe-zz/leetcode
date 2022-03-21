package com.example.leetcode.challenge.test2022.march;

import com.example.leetcode.model.ListNode;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l12 = new ListNode(2);
        ListNode l11 = new ListNode(2,l12);
        ListNode l1 = new ListNode(1,l11);
        ListNode l22 = new ListNode(1);
        ListNode l21 = new ListNode(3,l22);
        ListNode l2 = new ListNode(1,l21);
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        mergeTwoSortedLists.mergeTwoLists(l12,l22);

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode();
        ListNode p = res;

        while (list1 != null || list2 != null) {
            if(list1 == null ) {
                p.next = list2;
                list2 = list2.next;
            } else if(list2 == null ) {
                p.next = list1;
                list1 = list1.next;
            } else {
                if(list1.val > list2.val) {
                    p.next = list2;
                    list2 = list2.next;
                } else {
                    p.next = list1;
                    list1 = list1.next;
                }
            }
            p = p.next;
        }
        return res.next;
    }
}
