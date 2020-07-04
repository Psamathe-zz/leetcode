package com.example.leetcode.medium;

import java.util.Stack;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {
    public static void main(String[] args) {

    }


    public ListNode rotateRight(ListNode head, int k) {
        int size = 0;
        ListNode p = head;
        Stack<ListNode> stack = new Stack<>();
        while (p != null){
            stack.add(p);
            size++;
            p = p.next;
        }

        if(head == null ||size == 1 || k==0)
            return head;
        k = k % size;

        p = null;
        ListNode pre = null;
        ListNode last = null;
        while ( k > 0){
            if(p != null) {
                pre = p;
            }
            p = stack.pop();
            p.next = pre;
            if(pre == null){
                last = p;
            }
            k--;
        }
        head = p;
        p = null;
        pre = null;
        while (!stack.isEmpty()){
            if(p != null) {
                pre = p;
            }
            p = stack.pop();
            p.next = pre;
        }
        if(last != null)
            last.next = p;
        return head;
    }


    /**
     * faster solution
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightV1(ListNode head, int k) {
        if (head == null) return null;
        int count = 1;
        ListNode hp = head;
        while( head.next != null) {
            count ++;
            head = head.next;
        }
        ListNode end =  head;

        int fs = count - k % count;
        head = hp;
        count = 1;
        ListNode cur = null;
        while (count <= fs) {
            cur = head;
            head = head.next;
            count ++;
        }

        if(cur.next == null) return hp;

        ListNode result = cur.next;
        cur.next = null;
        end.next = hp;
        return result;
    }

    public class ListNode {
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
