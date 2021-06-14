package com.example.leetcode.challenge.test2021.june.week2;


/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class ConstructBinaryTree {
    public static void main(String[] args) {
        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();
        TreeNode res = constructBinaryTree.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        res.toString();
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if(preStart > preEnd)
            return null;
        TreeNode node = new TreeNode(preorder[preStart]);
        int i = inStart;
        for ( ; i <= inEnd ; i++) {
            if(inorder[i] == preorder[preStart])
                break;
        }
        if(i != inStart)
            node.left = helper(preorder, inorder, preStart + 1,  preStart + i - inStart, inStart, i - 1);
        if(i != inEnd)
            node.right = helper(preorder, inorder, preStart + i - inStart + 1, preEnd, i + 1, inEnd);
        return node;
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
