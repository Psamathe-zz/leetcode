package com.example.leetcode.challenge.test2021.december;

import com.example.leetcode.model.ListNode;

/**
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
 *
 * Return the decimal value of the number in the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 * Example 2:
 *
 * Input: head = [0]
 * Output: 0
 * Example 3:
 *
 * Input: head = [1]
 * Output: 1
 * Example 4:
 *
 * Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * Output: 18880
 * Example 5:
 *
 * Input: head = [0,0]
 * Output: 0
 */
public class ConvertBinaryNumberInLinkedListToInteger {
    public static void main(String[] args) {

    }

    public int getDecimalValue(ListNode head) {
        ListNode p = head;
        int res= 0;
        while (p != null){
            res = res * 2  + p.val;
            p = p.next;
        }
        return res;
    }
}
