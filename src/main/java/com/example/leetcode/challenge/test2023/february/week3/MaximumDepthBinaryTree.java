package com.example.leetcode.challenge.test2023.february.week3;

import com.example.leetcode.model.TreeNode;

/**
 * iven the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 *
 * Input: root = [1,null,2]
 * Output: 2
 */
public class MaximumDepthBinaryTree {
    public static void main(String[] args) {

    }

    int res = 0;
    public int maxDepth(TreeNode root) {
        helper(root, 0);
        return res;
    }

    public void helper(TreeNode node, int cur) {
        if(node == null) {
            res = Math.max(res, cur);
            return;
        }

        helper(node.left, cur+1);
        helper(node.right, cur+1);
    }
}
