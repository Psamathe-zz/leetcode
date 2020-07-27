package com.example.leetcode.challenge.July.week4;

import com.example.leetcode.medium.ConstructBinaryTree;

import java.util.Arrays;

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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode convert(Integer[] array) {
        int floor = 0, count = 0;
        TreeNode[] treeNodes = new TreeNode[array.length];
        while (array != null && count < array.length) {
            int start = (int) Math.pow(2, floor) - 1;
            int end = (int) Math.pow(2, floor + 1) - 1;
            if (end > array.length) {
                end = array.length;
            }
            for (int i = start; i < end; i++) {
                if (array[i] != null) {
                    treeNodes[i] = new TreeNode(array[i]);
                } else {
                    treeNodes[i] = null;
                }
                if (i > 0) {
                    int parent = (i - 1) / 2;
                    if (parent >= 0) {
                        TreeNode pNode = treeNodes[parent];
                        if (pNode != null) {
                            if (i % 2 == 1) {
                                pNode.left = treeNodes[i];
                            } else {
                                pNode.right = treeNodes[i];
                            }
                        }
                    }
                }
                count++;
            }
            floor++;
        }
        return treeNodes[0];
    }
}
