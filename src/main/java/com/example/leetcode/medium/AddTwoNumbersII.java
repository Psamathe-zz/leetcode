package com.example.leetcode.medium;

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
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

        ListNode l14 = new ListNode(3);
        ListNode l13 = new ListNode(4,l14);
        ListNode l12 = new ListNode(2,l13);
        ListNode l1 = new ListNode(7,l12);
        ListNode l23 = new ListNode(4);
        ListNode l22 = new ListNode(6,l23);
        ListNode l2 = new ListNode(5,l22);

        AddTwoNumbersII addTwoNumbersII = new AddTwoNumbersII();
        ListNode result = addTwoNumbersII.addTwoNumbers(l1,l2);
        System.out.println(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1!= null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2!= null){
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int a;
        int b;
        int next = 0;
        int value;
        ListNode node = null;
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            a = 0;
            b = 0;
            if(!stack1.isEmpty()){
                a = stack1.pop();
            }
            if(!stack2.isEmpty()){
                b = stack2.pop();
            }
            value = (a + b + next) % 10;
            next = (a + b + next) / 10;
            if(node == null){
                node = new ListNode(value);
            } else {
                node = new ListNode(value,node);
            }
        }
        if(next != 0)
            node = new ListNode(next,node);
        return node;

    }
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
