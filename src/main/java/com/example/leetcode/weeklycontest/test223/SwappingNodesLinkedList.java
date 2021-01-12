package com.example.leetcode.weeklycontest.test223;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * Example 3:
 *
 * Input: head = [1], k = 1
 * Output: [1]
 * Example 4:
 *
 * Input: head = [1,2], k = 1
 * Output: [2,1]
 * Example 5:
 *
 * Input: head = [1,2,3], k = 2
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 105
 * 0 <= Node.val <= 100
 */
public class SwappingNodesLinkedList {
    public static void main(String[] args) {

    }

    public ListNode swapNodes(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        ListNode a = null;
        ListNode b = null;
        int index = 0;
        while ( p != null){
            stack.add(p);
            index++;
            if(index == k)
                a = p;
            p = p.next;
        }

        if(index - k + 1 == k)
            return head;
        else if(k > index - k + 1){
            return swapNodes(head,index - k + 1);
        }
        ListNode next = null;
        ListNode cur = null;
        int size = index;
        index = 0;
        while (!stack.isEmpty()){
            index++;
            if(index == k){
                b = stack.pop();
                cur = a;
            } else if(index == size - k + 1){
                stack.pop();
                cur = b;
            } else {
                cur = stack.pop();
            }
            cur.next = next;
            next = cur;
        }

        return cur;
    }

    /**
     * faster solution
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodesV1(ListNode head, int k) {
        int listLength = 0;
        ListNode frontNode = null, endNode = null;
        ListNode currentNode = head;
        while (currentNode != null) {
            listLength++;
            if (listLength == k) {
                frontNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        int count = 0;
        currentNode = head;
        while (count != listLength - k) {
            count++;
            currentNode = currentNode.next;
        }
        endNode = currentNode;
        // swap front node and end node values
        int tempNode = frontNode.val;
        frontNode.val = endNode.val;
        endNode.val = tempNode;
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
