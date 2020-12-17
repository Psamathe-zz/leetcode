package com.example.leetcode.challenge.December.week1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 *
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
 *
 * Example:
 *
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 *
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 */
public class Solution {

    int len;
    ListNode head;
    Random rand;
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        rand = new Random();
        len = 0;
        ListNode cur = head;
        this.head = head;
        while (cur != null) {
            ++len;
            cur = cur.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int t = rand.nextInt(len);
        ListNode cur = this.head;
        while (t > 0) {
            --t;
            cur = cur.next;
        }
        return cur.val;
    }


    /**
     * faster solution
     */
    private ArrayList<Integer> range = new ArrayList<>();

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head,int p) {
        while (head != null) {
            this.range.add(head.val);
            head = head.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandomV1() {
        int pick = (int)(Math.random() * this.range.size());
        return this.range.get(pick);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
