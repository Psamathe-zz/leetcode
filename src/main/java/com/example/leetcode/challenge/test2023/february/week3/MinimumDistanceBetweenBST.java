package com.example.leetcode.challenge.test2023.february.week3;

import com.example.leetcode.easy.MinimumDistanceBetweenBSTNodes;
import com.example.leetcode.model.TreeNode;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 */
public class MinimumDistanceBetweenBST {
    public static void main(String[] args) {

    }

    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;
    public int minDiffInBST(TreeNode root) {
        inOrderBST(root);
        return minDiff;
    }

    public void inOrderBST(TreeNode root){
        if (root == null)
            return;
        inOrderBST(root.left);
        if (prev != null)
            minDiff = Math.min(minDiff,root.val - prev.val);
        prev = root;
        inOrderBST(root.right);
    }
}
