package com.example.leetcode.medium;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *    [2,3,1]
 * [3,2,1]
 * Output
 * [2,null,1,3]
 * Expected
 * [1,2,null,null,3]
 */
public class ConstructBinaryTreeFromInorderPostorderTraversal {
    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        ConstructBinaryTreeFromInorderPostorderTraversal constructBinaryTreeFromInorderPostorderTraversal = new ConstructBinaryTreeFromInorderPostorderTraversal();
        TreeNode root = constructBinaryTreeFromInorderPostorderTraversal.buildTree(inorder, postorder);
        System.out.println(root);
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
