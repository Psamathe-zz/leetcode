package com.example.leetcode.medium;

/**
 * Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)
 *
 * A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.
 *
 * Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 *
 * Output: [1,2,3,4,null,null,7,8,9,null,14]
 * Example 2:
 *
 *
 * Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 *
 * Output: [5,4,8,11,null,17,4,7,null,null,null,5]
 *
 *
 * Example 3:
 *
 *
 * Input: root = [1,2,-3,-5,null,4,null], limit = -1
 *
 * Output: [1,null,-3,4]
 *
 *
 * Note:
 *
 * The given tree will have between 1 and 5000 nodes.
 * -10^5 <= node.val <= 10^5
 * -10^9 <= limit <= 10^9
 */
public class InsufficientNodesRootLeafPaths {
    public static void main(String[] args) {

    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (helper(root, limit))
            return root;
        return null;
    }

    public boolean helper(TreeNode node,int limit){
        if (node == null)
            return false;

        if(node.left == null && node.right == null){
            if(node.val < limit)
                return false;
            else
                return true;
        }
        boolean lret = helper(node.left,limit-node.val);
        boolean rret = helper(node.right,limit-node.val);

        if (!lret) node.left = null;
        if (!rret) node.right = null;

        return lret || rret;
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
