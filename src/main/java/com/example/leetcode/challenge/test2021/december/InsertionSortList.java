package com.example.leetcode.challenge.test2021.december;

import com.example.leetcode.model.ListNode;

/**
 *Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
 *
 * The steps of the insertion sort algorithm:
 *
 * Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
 * It repeats until no input elements remain.
 * The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
 *
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
 */
public class InsertionSortList {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4250107.html
     * 链表的插入排序实现原理很简单，就是一个元素一个元素的从原链表中取出来，然后按顺序插入到新链表中
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode preHead = new ListNode();
        ListNode cur;
        ListNode temp;

        while (null != head) {
            temp = head.next;
            cur = preHead;
            while (null != cur.next && cur.next.val <= head.val) {
                cur = cur.next;
            }
            head.next = cur.next;
            cur.next = head;
            head = temp;
        }
        return preHead.next;
    }
}
