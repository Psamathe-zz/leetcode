package com.example.leetcode.challenge.test2021.august.week1;


import com.example.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * Example 3:
 *
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 * [-2,null,-3]
 * -5
 *
 */
public class PathSumII {
    public static void main(String[] args) {
        PathSumII pathSumII = new PathSumII();
        Integer[] input = new Integer[]{-2,null,-3};
        TreeNode root = TreeNode.convert(input);
        pathSumII.pathSum(root, -5);
    }

    List<List<Integer>> res;
    Stack<Integer> stack;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        stack = new Stack<>();
        helper(root, targetSum, 0);
        return res;
    }

    public void helper(TreeNode node, int targetSum, int sum){
        if(node == null)
            return;
        stack.add(node.val);
        sum += node.val;
        if(node.left != null) {
            helper(node.left, targetSum, sum);
        }
        if(node.right != null) {
            helper(node.right, targetSum, sum);
        }
        if(node.left == null && node.right == null && sum == targetSum){
            res.add(new ArrayList(stack));
        }
        stack.pop();
    }
}
