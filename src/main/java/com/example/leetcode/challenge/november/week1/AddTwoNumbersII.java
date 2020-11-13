package com.example.leetcode.challenge.november.week1;

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (l1 != null){
            stack1.add(l1);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.add(l2);
            l2 = l2.next;
        }
        ListNode p = null;
        ListNode pre = null;
        int val = 0;
        int preVal = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            val = preVal;
            val += stack1.isEmpty()?0:stack1.pop().val;
            val += stack2.isEmpty()?0:stack2.pop().val;
            preVal = val%10;
            val = val/10;
            p = new ListNode(val,pre);
            pre = p;
        }
        if(preVal != 0)
            p = new ListNode(preVal,pre);
        return p;
    }


    /**
     * faster solution
     * @param l
     * @return
     */
    private ListNode reverse(ListNode l){
        ListNode prev = null;
        ListNode cur = l;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    public ListNode addTwoNumbersV1(ListNode l1, ListNode l2) {
        ListNode revL1 = reverse(l1);
        ListNode it1 = revL1;
        ListNode it2 = reverse(l2);
        ListNode prev = null;
        int carry = 0;
        while(it1 != null || it2 != null){
            if(it1 == null){
                it1 = it2;
                it2 = null;
            }
            it1.val += ((it2 != null) ? it2.val : 0) + carry;
            carry = it1.val/10;
            it1.val %= 10;
            if(prev != null){
                prev.next = it1;
            }
            prev = it1;
            it1 = it1.next;
            if(it2 != null){
                it2 = it2.next;
            }
        }
        if(carry != 0){
            prev.next = new ListNode(carry);
        }
        return reverse(revL1);
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


}
