package com.example.leetcode.challenge.test2021.november;

import com.example.leetcode.model.TreeNode;

/**
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Example 2:
 *
 * Input: root = [1]
 * Output: 0
 */
public class SumLeftLeaves {
    public static void main(String[] args) {

    }

    int res;
    public int sumOfLeftLeaves(TreeNode root) {
        res = 0;
        if(root == null)
            return 0;
        helper(root, false);
        return res;
    }

    public void helper(TreeNode node, boolean isLeft){
        if(node.left == null && node.right == null && isLeft)
            res += node.val;
        if(node.left != null)
            helper(node.left, true);
        if(node.right != null)
            helper(node.right, false);
    }
}
