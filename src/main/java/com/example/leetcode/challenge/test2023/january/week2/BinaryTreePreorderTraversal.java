package com.example.leetcode.challenge.test2023.january.week2;

import com.example.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {

    }

    List<Integer> res;
    public List<Integer> preorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        helper(root);
        return res;
    }

    public void helper(TreeNode node ) {
        if(node == null)
            return;
        res.add(node.val);
        helper(node.left);
        helper(node.right);
    }
}
