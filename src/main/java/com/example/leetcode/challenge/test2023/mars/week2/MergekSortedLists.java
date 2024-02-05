package com.example.leetcode.challenge.test2023.mars.week2;

import com.example.leetcode.model.ListNode;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 */
public class MergekSortedLists {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode p = head;
        Integer index = null;
        do{
            index = findNext(lists);
            if(index != null) {
                p.next = lists[index];
                lists[index] = lists[index].next;
                p = p.next;
            }
        } while (index != null);



        return head.next;
    }
    Integer findNext(ListNode[] list) {
        Integer res = null;
        Integer cur = Integer.MAX_VALUE;
        for (int i = 0; i < list.length; i++) {
            if(list[i] != null &&  list[i].val < cur) {
                res = i;
                cur = list[i].val;
            }
        }
        return res;
    }
}
