package com.example.leetcode.challenge.test2023.mars.week3;

import com.example.leetcode.model.TreeNode;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 */
public class SymmetricTree {
    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return false;
        return isSymmetric(root.left,root.right);
    }

    public boolean isSymmetric (TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
