package com.example.leetcode.easy;


/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 *
 * Input: root = []
 * Output: true
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{3,9,20,null,null,15,7};
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        TreeNode root = balancedBinaryTree.convert(array);
        balancedBinaryTree.isBalanced(root);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(helper(root.left) - helper(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int helper(TreeNode node){
        if(node == null)
            return 0;
        return Math.max(helper(node.left),helper(node.right)) + 1;
    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public boolean isBalancedV1(TreeNode root) {
        if (checkDepth(root) == -1) return false;
        else return true;
    }
    int checkDepth(TreeNode root) {
        if (null == root)
            return 0;
        int left = checkDepth(root.left);
        if (left == -1)
            return -1;
        int right = checkDepth(root.right);
        if (right == -1)
            return -1;
        int diff = Math.abs(left - right);
        if (diff > 1)
            return -1;
        else return 1 + Math.max(left, right);
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
