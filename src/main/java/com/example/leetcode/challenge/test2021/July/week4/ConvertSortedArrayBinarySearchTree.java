package com.example.leetcode.challenge.test2021.July.week4;


import com.example.leetcode.model.TreeNode;

/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 *
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 *
 *
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
 *
 */
public class ConvertSortedArrayBinarySearchTree {
    public static void main(String[] args) {
        ConvertSortedArrayBinarySearchTree convertSortedArrayBinarySearchTree = new ConvertSortedArrayBinarySearchTree();
        TreeNode root = convertSortedArrayBinarySearchTree.sortedArrayToBST(new int[]{-10,-3,0,5,9});
        System.out.println(root);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int length = nums.length;
        return helper(nums, 0 , length - 1);
    }

    public TreeNode helper(int[] nums, int start, int end) {
        if(start > end)
            return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        TreeNode left = helper(nums, start, mid - 1);
        node.left = left;
        TreeNode right = helper(nums, mid + 1, end);
        node.right = right;
        return node;
    }
}
