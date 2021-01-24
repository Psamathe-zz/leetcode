package com.example.leetcode.challenge.test2021.January.week4;



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
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 */
public class MergekSortedLists {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        int size = lists.length;
        ListNode p = new ListNode();
        ListNode head = p;

        int minIndex;
        int min;
        while (true){
            min = Integer.MAX_VALUE;
            minIndex = -1;
            for (int i = 0; i < size; i++) {
                if(lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            if(minIndex == - 1){
                break;
            } else {
                System.out.println(minIndex);
                System.out.println(head.val);
                p.next = lists[minIndex];
                p = p.next;
                lists[minIndex] = lists[minIndex].next;
            }
        }
        return head.next;
    }

    public static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}
