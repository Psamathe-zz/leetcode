package com.example.leetcode.challenge.test2021.January.week1;


/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 *
 *
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesSortedListII {
    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current=head;
        ListNode previous=null;
        while (current!=null && current.next!=null) {
            boolean isDistinct=true;
            while (current.next!=null && current.val==current.next.val) {
                current.next=current.next.next;
                isDistinct=false;
            }
            if (!isDistinct) {
                if (current==head) {
                    head=current.next;
                    current=head;
                } else {
                    previous.next=current.next;
                    current=current.next;
                }
            } else {
                previous=current;
                current=current.next;
            }

        }
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
