package com.example.leetcode.challenge.test2020.may.week3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode root = new ListNode(1,node2);
        OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
        oddEvenLinkedList.oddEvenList(root);
    }

    public ListNode oddEvenList(ListNode head) {
        int index = 1;
        Queue<ListNode> stackOdd = new LinkedList<>();
        Queue<ListNode> stackEven = new LinkedList<>();
        ListNode temp = head;
        while (temp != null){
            if(index%2 == 1){
                stackOdd.add(temp);
            } else {
                stackEven.add(temp);
            }
            index++;
            temp = temp.next;
        }
        ListNode pre = null;
        ListNode result = null;
        while (!stackOdd.isEmpty()){
            temp = stackOdd.poll();
            if(pre != null){
                pre.next = temp;
            }
            pre = temp;
        }
        while (!stackEven.isEmpty()){
            temp = stackEven.poll();
            if(pre != null){
                pre.next = temp;
            }
            pre = temp;
        }
        if(pre != null){
            pre.next = null;
        }
        return head;
    }

    /**
     * faster solution
     * @param head
     * @return
     */
    public ListNode oddEvenListV1(ListNode head) {

        if (head == null) return null;

        ListNode odd = head;
        ListNode even= head.next;
        ListNode temp = even;

        while(even!=null&& even.next != null)
        {
            odd.next=even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = temp;
        return head;
    }
    /**
     * less memory
     * @param head
     * @return
     */
    public ListNode oddEvenListV3(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode first = head, second = head.next, secondhead = second;
        while (first != null && second != null && first.next != null && second.next != null) {
            first.next = second.next;
            second.next = first.next.next;
            first = first.next;
            second = second.next;
        }

        first.next = secondhead;
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
