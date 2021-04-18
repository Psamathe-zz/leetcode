package com.example.leetcode.challenge.test2021.april.week3;


import java.util.List;
import java.util.Stack;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Follow up: Could you do this in one pass?
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
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class RemoveNodeFromEnd {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while (p!=null){
            stack.add(p);
            p = p.next;
        }
        ListNode pre = null;
        while (!stack.isEmpty()){
            if(n != 1){
                p = stack.pop();
                p.next = pre;
                pre = p;
            } else {
                stack.pop();
            }
            n--;
        }
        return pre;
    }

    /**
     * better solution
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndV0(ListNode head, int n) {
        if(null == head || n <= 0){
            return head;
        }

        int i = 0;
        ListNode first = head;
        ListNode second = head;
        while(i < n && null != second){
            second = second.next;
            i++;
        }
        if (i < n)
            return head;

        if (null == second)
            return head.next;

        while(null != second.next){
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
