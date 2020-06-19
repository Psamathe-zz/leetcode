package com.example.leetcode.easy;

public class ConvertBSTToGreaterTree {
    public static void main(String[] args) {

    }

    int currentSum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null)
            return null;
        if(root.right != null)
            convertBST(root.right);
        int value = root.val;
        root.val += currentSum;
        currentSum += value;
        if(root.left != null)
            convertBST(root.left);
        return root;
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
}
