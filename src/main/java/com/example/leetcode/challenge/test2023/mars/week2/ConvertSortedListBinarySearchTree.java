package com.example.leetcode.challenge.test2023.mars.week2;

import com.example.leetcode.challenge.test2021.may.week1.ConvertSortedList;
import com.example.leetcode.model.ListNode;
import com.example.leetcode.model.TreeNode;

/**
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a
 * height-balanced
 *  binary search tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 * Example 2:
 *
 * Input: head = []
 * Output: []
 */
public class ConvertSortedListBinarySearchTree {
    public static void main(String[] args) {

    }

    public TreeNode sortedListToBST(ListNode head) {
        if (null == head) return null;
        return helper(head, null);
    }

    public TreeNode helper(ListNode head, ListNode tail){
        if (head == tail)
            return null;
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode cur = new TreeNode(slow.val);
        cur.left = helper(head, slow);
        cur.right = helper(slow.next, tail);
        return cur;
    }
}
