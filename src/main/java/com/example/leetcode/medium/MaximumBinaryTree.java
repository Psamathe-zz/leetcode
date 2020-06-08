package com.example.leetcode.medium;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * Note:
 * The size of the given array will be in the range [1,1000].
 */
public class MaximumBinaryTree {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,6,0,5};
        MaximumBinaryTree maximumBinaryTree = new MaximumBinaryTree();
        TreeNode root = maximumBinaryTree.constructMaximumBinaryTree(nums);
        root.toString();
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return help(nums,0,nums.length);
    }

    public TreeNode help(int[] nums, int start, int end){
        int index = start;
        for (int i = start; i < end; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        TreeNode node = new TreeNode(nums[index]);
        if(index > start)
            node.left = help(nums,start,index);
        if(index < end - 1)
            node.right = help(nums,index+1, end);
        return node;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
