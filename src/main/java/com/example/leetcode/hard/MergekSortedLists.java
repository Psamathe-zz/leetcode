package com.example.leetcode.hard;

import com.example.leetcode.medium.RemoveDuplicatesfromSortedListII;

import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */

public class MergekSortedLists {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        int size = lists.length;
        ListNode p = new ListNode();
        ListNode head = p;

        int minIndex;
        int min;
        while (true){
            min = Integer.MAX_VALUE;
            minIndex = -1;
            for (int i = 0; i < size; i++) {
                if(lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            if(minIndex == - 1){
                break;
            } else {
                System.out.println(minIndex);
                System.out.println(head.val);
                p.next = lists[minIndex];
                p = p.next;
                lists[minIndex] = lists[minIndex].next;
            }
        }
        return head.next;
    }


    /**
     * faster solution
     * @param lists
     * @return
     */
    public ListNode mergeKListsV1(ListNode[] lists) {
        //edge case
        if(lists==null || lists.length==0)
            return null;

        //why not -1
        ListNode head=new ListNode(Integer.MIN_VALUE);
        ListNode dummy=head;

        //min heap
        PriorityQueue<ListNode> pq=new PriorityQueue<>((a, b)->a.val-b.val);

        for(ListNode elem:lists){
            if(elem!=null)
                pq.add(elem);
        }

        while(!pq.isEmpty()){
            ListNode min=pq.poll();
            head.next=min;
            head=head.next;
            if(min.next!=null){
                pq.add(min.next);
            }
        }
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
