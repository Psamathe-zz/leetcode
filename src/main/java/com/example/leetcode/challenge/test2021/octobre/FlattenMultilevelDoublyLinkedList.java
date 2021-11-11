package com.example.leetcode.challenge.test2021.octobre;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
 * Explanation:
 *
 * The multilevel linked list in the input is as follows:
 *
 *
 *
 * After flattening the multilevel linked list it becomes:
 *
 *
 * Example 2:
 *
 * Input: head = [1,2,null,3]
 * Output: [1,3,2]
 * Explanation:
 *
 * The input multilevel linked list is as follows:
 *
 *   1---2---NULL
 *   |
 *   3---NULL
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 */
public class FlattenMultilevelDoublyLinkedList {
    public static void main(String[] args) {
        FlattenMultilevelDoublyLinkedList flattenMultilevelDoublyLinkedList = new FlattenMultilevelDoublyLinkedList();
        //flattenMultilevelDoublyLinkedList.flatten(head);
    }

    public Node flatten(Node head) {
        Node p = head;
        Node pNext;
        Node pPre;
        while (p != null){
            if(p.child != null){
                pNext = p.next;
                Node childRes = flatten(p.child);
                p.next = childRes;
                childRes.prev = p;
                pPre = childRes;
                while (childRes != null){
                    pPre = childRes;
                    childRes = childRes.next;
                }
                pPre.next = pNext;

                if(pNext != null)
                    pNext.prev = pPre;
                p.child = null;
                p = pNext;
            } else
                p = p.next;
        }
        return head;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    };
}
