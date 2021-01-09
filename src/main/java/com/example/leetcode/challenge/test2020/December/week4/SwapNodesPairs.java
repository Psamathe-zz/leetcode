package com.example.leetcode.challenge.test2020.December.week4;


/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 */
public class SwapNodesPairs {
    public static void main(String[] args) {
        ListNode node3= new ListNode(3);
        ListNode node2= new ListNode(2, node3);
        ListNode node1= new ListNode(1, node2);
        SwapNodesPairs swapNodesPairs = new SwapNodesPairs();
        swapNodesPairs.swapPairs(node1);
    }

    public ListNode swapPairs(ListNode head) {
        ListNode cur = head;
        ListNode sui;
        ListNode temp;
        ListNode res = null;
        ListNode pre = null;
        while (cur != null) {
            sui = cur.next;
            if(sui == null ) {
                if(res == null)
                    res = cur;
                break;
            }
            temp = sui.next;
            sui.next = cur;
            if(pre != null)
                pre.next = sui;
            if(res == null)
                res = sui;
            cur.next = temp;
            pre = cur;
            cur = cur.next;
        }
        return res;
    }

    /**
     * faster solution
     */

    public ListNode swapPairsV1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = swapPairsV1(head.next.next);
        ListNode temp = head.next;
        head.next = node;
        temp.next = head;
        return temp;
    }

    public static class ListNode {
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
