package com.example.leetcode.challenge.October.week1;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
        if(head == null)
            return null;
        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = head;
        while (cur != null){
            deque.addLast(cur);
            cur = cur.next;
        }
        k = k % deque.size();

        while (k>0){
            deque.addFirst(deque.pollLast());
            k--;
        }

        ListNode res = deque.getFirst();
        cur = res;
        while (!deque.isEmpty()){
            cur.next = deque.pollFirst();
            cur = cur.next;
        }
        cur.next = null;
        return res;
    }

    /**
     * faster solution
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightV1(ListNode head, int k)
    {
        ListNode a=head,b=head,c=head;
        int ctr=0;
        if(head==null || k==0)
            return head;
        while(c!=null) {
            ctr++;
            c=c.next;
        }
        k=k%ctr;
        while(k>0) {
            b=b.next;
            k--;
        }
        while(b.next!=null) {
            a=a.next;
            b=b.next;
        }
        b.next=head;
        head=a.next;
        a.next=null;
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
