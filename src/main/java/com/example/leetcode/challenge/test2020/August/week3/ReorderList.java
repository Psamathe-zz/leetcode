package com.example.leetcode.challenge.test2020.August.week3;

import java.util.Stack;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {
    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        ListNode p4 = new ListNode(4);
        ListNode p3 = new ListNode(3,p4);
        ListNode p2 = new ListNode(2,p3);
        ListNode p1 = new ListNode(1,p2);
        reorderList.reorderList(p1);
        p1.toString();
    }

    public void reorderList(ListNode head) {
        if (head==null || head.next==null || head.next.next == null)
            return;
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        int cnt = ((int)stack.size() - 1) / 2;
        cur = head;
        while (cnt-- > 0) {
            ListNode t = stack.pop();
            ListNode next = cur.next;
            cur.next = t;
            t.next = next;
            cur = next;
        }
        stack.peek().next = null;
    }

    /**
     * faster solution
     * @param head
     */
    public void reorderListV1(ListNode head) {
        if (head == null) return;

        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
