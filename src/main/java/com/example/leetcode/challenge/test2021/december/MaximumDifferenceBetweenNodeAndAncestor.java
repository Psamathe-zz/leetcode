package com.example.leetcode.challenge.test2021.december;

import com.example.leetcode.model.TreeNode;

/**
 * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 *
 * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Example 2:
 *
 *
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 *
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    public static void main(String[] args) {

    }

    int res = 0;
    public int maxAncestorDiff(TreeNode root) {
        if(root == null)
            return 0;
        helper(root.left, root.val, root.val);
        helper(root.right, root.val, root.val);
        return res;
    }

    public void helper(TreeNode node, int max, int  min) {
        if(node == null)
            return;
        if(node.val > max)
            max = node.val;
        if(node.val < min)
            min = node.val;
        int v = Math.max(Math.abs(max - node.val), Math.abs(min - node.val));
        res = Math.max(res, v);

        helper(node.left, max, min);
        helper(node.right, max, min);
    }

}
