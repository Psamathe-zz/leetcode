package com.example.leetcode.challenge.test2021.november;

import com.example.leetcode.model.TreeNode;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 */
public class ConstructBinaryTreeFromInorderPostorderTraversal {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = help(inorder,postorder,0,0,postorder.length);
        return root;
    }

    public TreeNode help(int[] inorder, int[] postorder, int startin,int startpost,int length) {
        if(length == 0)
            return null;
        if(length == 1)
            return new TreeNode(inorder[startin]);;
        int newLength;
        for(newLength=0;newLength < length;newLength++){
            if(inorder[startin + newLength] == postorder[startpost + length - 1]){
                break;
            }
        }
        TreeNode root = new TreeNode(inorder[startin + newLength]);
        root.left = help(inorder, postorder, startin, startpost, newLength);
        root.right = help(inorder, postorder, startin + newLength + 1, startpost + newLength, length - newLength - 1);

        return root;
    }
}
