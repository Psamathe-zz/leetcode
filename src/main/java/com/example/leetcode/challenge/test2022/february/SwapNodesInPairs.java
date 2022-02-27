package com.example.leetcode.challenge.test2022.february;

import com.example.leetcode.model.ListNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {

    }

    public ListNode swapPairs(ListNode head) {
        Queue<ListNode> queue1 = new LinkedList<>();
        Queue<ListNode> queue2 = new LinkedList<>();
        int index = 0;
        ListNode p = head;
        while (p != null) {
            if(index % 2 == 0)
                queue1.add(p);
            else
                queue2.add(p);
            p = p.next;
            index++;
        }

        ListNode res = new ListNode();
        p = res;
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            if(!queue2.isEmpty()) {
                p.next = queue2.poll();
                p = p.next;
                p.next = null;
                System.out.println(p.val);
            }

            if(!queue1.isEmpty()) {
                p.next = queue1.poll();
                p = p.next;
                p.next = null;
                System.out.println(p.val);
            }
        }

        return res.next;
    }
}
