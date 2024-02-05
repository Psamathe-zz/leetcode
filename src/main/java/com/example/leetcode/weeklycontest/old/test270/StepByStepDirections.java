package com.example.leetcode.weeklycontest.old.test270;

import com.example.leetcode.model.TreeNode;

/**
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 * Example 2:
 *
 *
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 */
public class StepByStepDirections {
    public static void main(String[] args) {
        StepByStepDirections stepByStepDirections = new StepByStepDirections();
        TreeNode root = TreeNode.convert(new Integer[]{2,1});
        stepByStepDirections.getDirections(root,2, 1);
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        helper(root, startValue, stringBuilder1);
        helper(root, destValue, stringBuilder2);

        int length1 = stringBuilder1.length();
        int length2 = stringBuilder2.length();

        int length = Math.min(length1, length2);
        int i = 0;
        for (; i < length; i++) {
            if(stringBuilder1.charAt(i) != stringBuilder2.charAt(i)) {
                break;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int j = i; j < length1; j++) {
            res.append('U');
        }
        for (int j = i; j < length2; j++) {
            res.append(stringBuilder2.charAt(j));
        }

        return res.toString();
    }

    public boolean helper(TreeNode node, int target, StringBuilder stringBuilder) {
        if(node == null)
            return false;
        if(node.val == target)
            return true;

        boolean left = helper(node.left, target, stringBuilder.append('L'));
        if(left)
            return left;
        stringBuilder.setLength(stringBuilder.length() - 1);


        boolean right = helper(node.right, target, stringBuilder.append('R'));
        if(right)
            return right;
        stringBuilder.setLength(stringBuilder.length() - 1);
        return false;
    }
}
