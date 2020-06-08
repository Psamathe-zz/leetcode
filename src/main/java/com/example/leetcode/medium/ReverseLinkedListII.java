package com.example.leetcode.medium;

public class ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(3, node5);
        int m = 1;
        int n = 2;
        ReverseLinkedListII reverseLinkedListII = new ReverseLinkedListII();
        ListNode result = reverseLinkedListII.reverseBetween(head, m, n);
        result.toString();
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode newhead = new ListNode(-1);
        newhead.next = head;

        if (head == null || head.next == null)
            return newhead.next;

        ListNode startpoint = newhead;//startpoint指向需要开始reverse的前一个
        ListNode node1 = null;//需要reverse到后面去的节点
        ListNode node2 = null;//需要reverse到前面去的节点

        for (int i = 0; i < n; i++) {
            if (i < m - 1) {
                startpoint = startpoint.next;//找真正的startpoint
            } else if (i == m - 1) {//开始第一轮
                node1 = startpoint.next;
                node2 = node1.next;
            } else {
                node1.next = node2.next;//node1交换到node2的后面
                node2.next = startpoint.next;//node2交换到最开始
                startpoint.next = node2;//node2作为新的点
                node2 = node1.next;//node2回归到node1的下一个，继续遍历
            }
        }
        return newhead.next;
    }


    /**
     * less memory
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetweenV2(ListNode head, int m, int n) {

        if (head == null || head.next == null) return head;

        int counter = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode firstEnd = dummy;

        while (counter < m) {
            firstEnd = firstEnd.next;
            counter++;
        }

        ListNode p1 = firstEnd.next, tempHead = p1, p2 = p1.next;
        counter = m;

        // Reverse list in place while still in m to n range
        while (p2 != null && counter < n) {
            p1.next = p2.next;
            p2.next = tempHead;

            tempHead = p2;
            p2 = p1.next;
            counter++;
        }

        firstEnd.next = tempHead; // Reattach first part with modified second part

        return dummy.next;
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
