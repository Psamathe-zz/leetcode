package com.example.leetcode.challenge.test2021.march.week2;

import java.util.Stack;

/**
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * Example 3:
 *
 * Input: head = [1], k = 1
 * Output: [1]
 * Example 4:
 *
 * Input: head = [1,2], k = 1
 * Output: [2,1]
 * Example 5:
 *
 * Input: head = [1,2,3], k = 2
 * Output: [1,2,3]
 */
public class SwappingNodesLinkedList {
    public static void main(String[] args) {

    }

    public ListNode swapNodes(ListNode head, int k) {
        int index = 1;
        ListNode p = head;
        Stack<ListNode> stack = new Stack<>();
        ListNode p1 = null;
        ListNode p2 = null;
        while ( p != null){
            stack.push(p);
            if(index == k)
                p1 = p;
            p = p.next;
            index++;
        }
        index = 1;
        while (!stack.isEmpty()){
            p = stack.pop();
            if(index == k){
                p2 = p;
                break;
            }
            index++;
        }
        System.out.println(p1);
        System.out.println(p2);
        int v = p1.val;
        p1.val = p2.val;
        p2.val = v;
        return head;
    }

    /**
     * 那么当前面的那个iterator走到结尾时，后面的那个距离结尾正好是k个(即倒数第k个)
     * faster solution
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodesV0(ListNode head, int k) {
        ListNode fast = head, slow =head;

        ListNode n1, n2;
        for(int i=0; i<k-1; i++){
            fast = fast.next;
        }
        n1 = fast;
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        n2 = slow;
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
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
