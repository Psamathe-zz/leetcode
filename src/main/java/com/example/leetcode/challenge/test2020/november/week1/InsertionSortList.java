package com.example.leetcode.challenge.test2020.november.week1;


/**
 * Sort a linked list using insertion sort.
 *
 *
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 *
 *
 * Algorithm of Insertion Sort:
 *
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class InsertionSortList {
    public static void main(String[] args) {
        ListNode p3 = new ListNode(3);
        ListNode p2 = new ListNode(1,p3);
        ListNode p1 = new ListNode(2,p2);
        ListNode head = new ListNode(4,p1);
        InsertionSortList insertionSortList = new InsertionSortList();
        insertionSortList.insertionSortListV1(head);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4250107.html
     * 链表的插入排序实现原理很简单，就是一个元素一个元素的从原链表中取出来，然后按顺序插入到新链表中
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode preHead = new ListNode(-1);
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


    /**
     * faster solution
     * @param head
     * @return
     */
    public ListNode insertionSortListV1(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while(head != null){
            ListNode temp = head.next;
            if(prev.val >= head.val){
                prev = dummy;
            }
            while(prev.next != null && prev.next.val < head.val){
                prev = prev.next;
            }
            head.next = prev.next;
            prev.next = head;
            head = temp;
        }
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
