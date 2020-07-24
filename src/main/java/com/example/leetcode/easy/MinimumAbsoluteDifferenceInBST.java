package com.example.leetcode.easy;

/**
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * Output:
 * 1
 *
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 *
 *
 * Note:
 *
 * There are at least two nodes in this BST.
 * This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinimumAbsoluteDifferenceInBST {
    public static void main(String[] args) {

    }
    int res = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        int left;
        int right;
        if(root.left != null) {
            left = getLeft(root.left);
            getMinimumDifference(root.left);
            res = Math.min(res,root.val - left);
        }
        if(root.right != null) {
            right = getRight(root.right);
            getMinimumDifference(root.right);
            res = Math.min(res,right - root.val);
        }
        return res;
    }

    public int getLeft(TreeNode node){
        while (node.right != null){
            node = node.right;
        }
        return node.val;
    }

    public int getRight(TreeNode node){
        while (node.left != null){
            node = node.left;
        }
        return node.val;
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
