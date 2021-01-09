package com.example.leetcode.challenge.test2020.april.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.
 * left has a value < node.val, and any descendant of node.right has a value > node.val.
 * Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 *
 *
 */
public class BinarySearchTree {
    public static void main(String[] args) {
        int[] preorder = new int[]{8,5,1,7,10,12};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        TreeNode root = binarySearchTree.bstFromPreorder(preorder);
        System.out.println(root);
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder.length == 0 || preorder == null)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for(int i = 1; i< preorder.length; i++){
            if(preorder[i] < root.val){
                left.add(preorder[i]);
            } else {
                right.add(preorder[i]);
            }
        }
        root.left = bstFromPreorder(left.stream().mapToInt(i->i).toArray());
        root.right = bstFromPreorder(right.stream().mapToInt(i->i).toArray());
        return root;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


    /**
     * faster
     */
    public TreeNode bstFromPreorderV1(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int start, int end) {
        if(start > end) return null;

        TreeNode node = new TreeNode(preorder[start]);
        int i;
        for(i=start;i<=end;i++) {
            if(preorder[i] > node.val)
                break;
        }

        node.left = helper(preorder, start+1, i-1);
        node.right = helper(preorder, i, end);
        return node;



    }
}
