package com.example.leetcode.medium;

import java.util.Stack;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {

    }

    public ListNode swapPairs(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;
        ListNode current1;
        ListNode current2;
        while (current != null){
            stack.push(current);
            current = current.next;
        }

        current = null;
        if(stack.size() % 2 == 1){
            current = stack.pop();
        }
        while (!stack.empty()){
            current1 = stack.pop();
            current2 = stack.pop();
            current1.next = current2;
            current2.next = current;
            current = current1;
        }
        return current;
    }


    /**
     * less memory
     * @param head
     * @return
     */

    public ListNode swapPairsV0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextPair = head.next.next;
        ListNode link = head;
        ListNode tmp = head;
        head = head.next;
        head.next = tmp;
        tmp.next = nextPair;
        while(nextPair != null && nextPair.next != null) {
            ListNode nextPair1 = nextPair.next.next;
            tmp = nextPair;
            nextPair = nextPair.next;
            link.next = nextPair;
            link = tmp;
            nextPair.next = tmp;
            tmp.next = nextPair1;
            nextPair = nextPair1;
        }
        // System.out.print(head.next.next.val);
        return head;

    }

    public ListNode swapPairsV1(ListNode head) {
        if (head == null)
            return null;

        if (head.next == null)
            return head;

        ListNode temp = head;

        head = head.next;
        temp.next = head.next;
        head.next = temp;

        head.next.next = swapPairsV1(head.next.next);

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
