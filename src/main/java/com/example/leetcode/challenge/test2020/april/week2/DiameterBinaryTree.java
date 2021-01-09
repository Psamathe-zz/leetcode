package com.example.leetcode.challenge.test2020.april.week2;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.left = node2;
        root.right = node3;

        node2.left = node4;
        node2.right = node5;

        node5.left = node6;

        DiameterBinaryTree diameterBinaryTree = new DiameterBinaryTree();
        int result = diameterBinaryTree.diameterOfBinaryTree(root);
        System.out.println(result);
    }

    public int diameterOfBinaryTree(TreeNode root) {

        if(root == null)
            return 0;
        else{
            int[] result = {0};
            depth(root,result);
            return result[0];
        }
    }

    public int depth(TreeNode node,int[] result){

        if(node == null)
            return 0;
        else{
            int left = depth(node.left,result);
            int right = depth(node.right,result);
            result[0] = Math.max(result[0], left + right);
            return Math.max(left, right) + 1;

        }
    }

}
