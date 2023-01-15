package com.example.leetcode.challenge.test2022.december;

import com.example.leetcode.model.TreeNode;

/**
 * Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 * Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 * Example 2:
 *
 *
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 * Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 */
public class RangeSumBST {
    public static void main(String[] args) {

    }

    int res = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        helper(root, low, high);
        return res;
    }

    private void helper(TreeNode node, int low, int high) {
        if(node == null) {
          return;
        } else if(node.val >= low && node.val <= high) {
            res += node.val;
            helper(node.left, low, high);
            helper(node.right, low, high);
        } else if(node.val < low ) {
            helper(node.right, low, high);
        } else {
            helper(node.left, low, high);
        }
    }
}
