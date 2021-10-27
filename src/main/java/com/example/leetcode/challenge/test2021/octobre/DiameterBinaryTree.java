package com.example.leetcode.challenge.test2021.octobre;

import com.example.leetcode.model.TreeNode;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 */
public class DiameterBinaryTree {
    public static void main(String[] args) {

    }

    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return res;
    }

    public int helper(TreeNode node){
       if(node == null)
           return 0;
       int left = helper(node.left);
       int right = helper(node.right);
       res = Math.max(res, left + right);
        System.out.println(left);
        System.out.println(right);
        System.out.println(left + right);
        return Math.max(left, right);
    }
}
