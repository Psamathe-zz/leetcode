package com.example.leetcode.challenge.test2021.octobre;

import com.example.leetcode.model.TreeNode;

/**
 * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 * Example 2:
 *
 * Input: preorder = [1,3]
 * Output: [1,null,3]
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public static void main(String[] args) {
        ConstructBinarySearchTreeFromPreorderTraversal constructBinarySearchTreeFromPreorderTraversal = new ConstructBinarySearchTreeFromPreorderTraversal();
        constructBinarySearchTreeFromPreorderTraversal.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode res = helper(preorder,0, preorder.length);
        return res;
    }

    public TreeNode helper(int[] preorder, int start, int end){
        TreeNode root = new TreeNode(preorder[start]);
        if(start == end)
            return root;
        int right;
        for (right = start + 1; right < end; right++) {
            if(preorder[right] > preorder[start])
                break;
        }
        if(start + 1 < right)
            root.left = helper(preorder, start + 1, right);
        if(right < end)
            root.right = helper(preorder, right, end);
        return root;
    }
}
