package com.example.leetcode.challenge.test2022.january;

import com.example.leetcode.model.TreeNode;

/**
 * You are given the root of a binary tree where each node has a value 0 or 1. Each root-to-leaf path represents a binary number starting with the most significant bit.
 *
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return the sum of these numbers.
 *
 * The test cases are generated so that the answer fits in a 32-bits integer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * Example 2:
 *
 * Input: root = [0]
 * Output: 0
 *
 */
public class SumRootToLeafBinaryNumbers {
    public static void main(String[] args) {

    }

    int sum;
    public int sumRootToLeaf(TreeNode root) {
        sum = 0;
        helper(root, 0);
        return sum;
    }

    public void helper(TreeNode node, int val) {
        val *= 2 + node.val;
        System.out.println(val);
        if(node.left == null && node.right == null)
            sum += val;
        if(node.left != null)
            helper(node.left, val);
        if(node.right != null)
            helper(node.right, val);
    }
}
