package com.example.leetcode.challenge.test2021.July.week3;


import com.example.leetcode.model.ListNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * Example 3:
 *
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 * Example 4:
 *
 * Input: head = [1], k = 1
 * Output: [1]
 */
public class ReverseNodes {
    public static void main(String[] args) {
        ReverseNodes reverseNodes = new ReverseNodes();
        ListNode p2 = new ListNode(3);
        ListNode p1 = new ListNode(2);
        ListNode head = new ListNode(1,p1);
        reverseNodes.reverseKGroup(head, 2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode res = new ListNode();
        ListNode pRes = res;
        Deque<ListNode> deque = new LinkedList<>();
        ListNode p = head;
        ListNode cur;
        while ( p != null){
            deque.addLast(p);
            p = p.next;
            if(deque.size() == k){
                while (!deque.isEmpty()){
                    cur = deque.pollLast();
                    pRes.next = cur;
                    pRes = pRes.next;
                }
                pRes.next = null;
            }
        }
        while (!deque.isEmpty()){
            cur = deque.pollFirst();
            pRes.next = cur;
            pRes = pRes.next;
        }
        pRes.next = null;
        return res.next;
    }
}
