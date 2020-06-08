package com.example.leetcode.medium;

import java.util.*;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * Return the linked list sorted as well.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 *
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class RemoveDuplicatesfromSortedListII {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(3);
        ListNode node4 = new ListNode(2,node5);
        ListNode node3 = new ListNode(1,node4);
        ListNode node2 = new ListNode(1,node3);
        ListNode lead = new ListNode(1,node2);
        RemoveDuplicatesfromSortedListII removeDuplicatesfromSortedListII = new RemoveDuplicatesfromSortedListII();
        ListNode result = removeDuplicatesfromSortedListII.deleteDuplicates(lead);
        System.out.println(result);
    }

    public ListNode deleteDuplicates(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        ListNode pointer = head;
        ListNode next;
        while (pointer != null){
            while(!stack.isEmpty() && stack.peek().val == pointer.val){
                list.add(pointer.val);
                stack.pop();
            }
            if(!list.contains(pointer.val)){
                stack.add(pointer);
            }
            pointer = pointer.next;
        }
        pointer = null;
        while(!stack.isEmpty()){
            next = pointer;
            pointer = stack.pop();
            pointer.next = next;
        }

        return pointer;
    }

    /**
     * faster solution
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesV1(ListNode head) {
        // Second Method: O(n^2) O(1)
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

    /**
     * less memory
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesV2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = head;
        Map<Integer, Integer> map = new HashMap<>();
        while(cur != null) {
            if (map.containsKey(cur.val)) {
                map.put(cur.val, map.get(cur.val)+1);
            } else {
                map.put(cur.val, 1);
            }
            cur = cur.next;
        }
        cur = dummyHead;
        while (cur != null && cur.next != null) {
            if (map.get(cur.next.val)>1) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }

        }
        return dummyHead.next;
    }
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
