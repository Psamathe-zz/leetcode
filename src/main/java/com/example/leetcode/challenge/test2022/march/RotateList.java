package com.example.leetcode.challenge.test2022.march;

import com.example.leetcode.model.ListNode;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 *
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 */
public class RotateList {
    public static void main(String[] args) {

    }

    public ListNode rotateRight(ListNode head, int k) {
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
}
