package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 *
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.
 * If such a j does not exist, the next larger value is 0.
 *
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 *
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 * Example 3:
 *
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 */
public class NextGreaterNodeInLinkedList {
    public static void main(String[] args) {
        ListNode node3 = new ListNode(5);
        ListNode node2 = new ListNode(3,node3);
        ListNode node1 = new ListNode(4,node2);
        ListNode node0 = new ListNode(7,node1);
        ListNode node = new ListNode(2,node0);
        NextGreaterNodeInLinkedList nextGreaterNodeInLinkedList = new NextGreaterNodeInLinkedList();
        int[] result = nextGreaterNodeInLinkedList.nextLargerNodes(node);
        System.out.println(result);
    }

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> result = new ArrayList<>();
        int max;
        while ((head) != null){
            max = findBig(head.next,head.val);
            result.add(max);
            head = head.next;
        }
        return result.stream().mapToInt(e->e).toArray();
    }

    public int findBig(ListNode node,int value ){
        int max = 0;
        while (node != null){
            if(node.val > value) {
                max = node.val;
                break;
            }
            node = node.next;
        }
        return max;
    }


    /**
     * faster solution
     * @param head
     * @return
     */
    public int[] nextLargerNodesV2(ListNode head) {
        if (head == null) return new int[1];

        return nextLargerNodesRec(head, 0);
    }

    public int[] nextLargerNodesRec(ListNode head, int pos) {
        if (head.next == null) {
            return new int[pos + 1];
        }

        int[] next = nextLargerNodesRec(head.next, pos + 1);
        if (head.next.val > head.val) {
            next[pos] = head.next.val;
        } else if (next[pos + 1] != 0) {
            for (int i = pos + 1; i < next.length; i++) {
                if (next[i] > head.val) {
                    next[pos] = next[i];
                    break;
                }
            }
        }

        return next;
    }
    /**
     * less memory
     * @param head
     * @return
     */
    public int[] nextLargerNodesV3(ListNode head) {
        ListNode temp = head;
        ArrayList<Integer> arr = new ArrayList<>();
        while(temp != null){
            ListNode max = temp.next;
            while(max != null && max.val <= temp.val)
                max = max.next;
            // temp.val = max == null ? 0 : max.val;
            arr.add(max == null ? 0 : max.val);
            temp = temp.next;
        }
        return arr.stream()
                .mapToInt(Integer::intValue)
                .toArray();
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
