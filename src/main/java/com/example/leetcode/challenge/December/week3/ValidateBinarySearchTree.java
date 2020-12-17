package com.example.leetcode.challenge.December.week3;


/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {

    }

    boolean res = true;
    public boolean isValidBST(TreeNode root) {
        helperLeft(root.left,root.val,Long.MIN_VALUE);
        helperRight(root.right,Long.MAX_VALUE,root.val);
        return res;
    }

    public void helperLeft(TreeNode node, long max, long min){
        if(node == null || !res)
            return;
        else if(node.val >= max || node.val <= min ) {
            res = false;
            return;
        } else {
            helperLeft(node.left,Math.min(node.val,max),min);
            helperRight(node.right,max,Math.max(node.val,min));
        }
    }

    public void helperRight(TreeNode node, long max, long min){
        if(node == null || !res)
            return;
        else if(node.val >= max || node.val <= min) {
            res = false;
            return;
        }else {
            helperLeft(node.left,Math.min(node.val,max),min);
            helperRight(node.right,max,Math.max(node.val,min));
        }
    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public boolean isValidBSTV2(TreeNode root) {
        if(root == null)
            return true;

        return helper(root, null, null);

    }

    public static boolean helper(TreeNode node, Integer lower, Integer upper) {

        if (node == null)
            return true;

        if (lower != null && node.val <= lower)
            return false;
        if (upper != null && node.val >= upper)
            return false;

        if (!helper(node.right, node.val, upper))
            return false;

        if (!helper(node.left, lower, node.val))
            return false;

        return true;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
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
