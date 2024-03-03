package com.example.leetcode.challenge.test2024.mars.week1;

import com.example.leetcode.model.ListNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Queue<ListNode> queue = new LinkedList<>();

        ListNode cur = head;
        while (cur != null) {
            queue.add(cur);
            cur = cur.next;
        }
        int size = queue.size() - n;

        ListNode res = new ListNode();
        cur = res;
        while (size > 0) {
            cur.next = queue.poll();
            cur = cur.next;
            size--;
        }
        queue.poll();

        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
        }
        cur.next = null;

        return res.next;
    }
}
