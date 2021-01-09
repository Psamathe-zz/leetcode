package com.example.leetcode.medium;


/**
 * Given a binary tree root and a linked list with head as the first node.
 * <p>
 * Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
 * <p>
 * In this context downward path means a path that starts at some node and goes downwards.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: true
 * Explanation: Nodes in blue form a subpath in the binary Tree.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: true
 * Example 3:
 * <p>
 * Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: false
 * Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= node.val <= 100 for each node in the linked list and binary tree.
 * The given linked list will contain between 1 and 100 nodes.
 * The given binary tree will contain between 1 and 2500 nodes.
 */
public class LinkedListInBinaryTree {

    public static void main(String[] args) {

    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null)
            return false;
        else if(helper(head,root))
            return true;
        else
            return isSubPath(head,root.left) || isSubPath(head,root.right);
    }

    public boolean helper(ListNode head, TreeNode node){
        ListNode currentListNode = head;
        TreeNode currentTreeNode = node;
        if(currentTreeNode== null || currentListNode.val != currentTreeNode.val)
            return false;
        else if(currentListNode.next != null)
            return helper(currentListNode.next,node.left) || helper(currentListNode.next,node.right);
        else
            return true;
    }

    public class ListNode {
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode convert(Integer[] array) {
        int floor = 0, count = 0;
        TreeNode[] treeNodes = new TreeNode[array.length];
        while (array != null && count < array.length) {
            int start = (int) Math.pow(2, floor) - 1;
            int end = (int) Math.pow(2, floor + 1) - 1;
            if (end > array.length) {
                end = array.length;
            }
            for (int i = start; i < end; i++) {
                if (array[i] != null) {
                    treeNodes[i] = new TreeNode(array[i]);
                } else {
                    treeNodes[i] = null;
                }
                if (i > 0) {
                    int parent = (i - 1) / 2;
                    if (parent >= 0) {
                        TreeNode pNode = treeNodes[parent];
                        if (pNode != null) {
                            if (i % 2 == 1) {
                                pNode.left = treeNodes[i];
                            } else {
                                pNode.right = treeNodes[i];
                            }
                        }
                    }
                }
                count++;
            }
            floor++;
        }
        return treeNodes[0];
    }
}
