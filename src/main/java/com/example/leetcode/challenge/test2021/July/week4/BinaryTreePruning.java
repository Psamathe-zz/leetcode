package com.example.leetcode.challenge.test2021.July.week4;


import com.example.leetcode.model.TreeNode;

/**
 * Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 *
 * A subtree of a node node is node plus every node that is a descendant of node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 * Example 2:
 *
 *
 * Input: root = [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 * Example 3:
 *
 *
 * Input: root = [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 */
public class BinaryTreePruning {
    public static void main(String[] args) {
        BinaryTreePruning binaryTreePruning= new BinaryTreePruning();
        TreeNode root = TreeNode.convert(new Integer[]{1,null,0,0,1});
        binaryTreePruning.pruneTree(root);
    }

    public TreeNode pruneTree(TreeNode root) {
        boolean res = helper(root);
        if (!res)
            return null;
        return root;
    }

    public boolean helper(TreeNode node){
        if(node == null)
            return false;
        boolean left = helper(node.left);
        if(!left)
            node.left = null;
        boolean right = helper(node.right);
        if(!right)
            node.right = null;
        return left || right || node.val == 1;
    }
}
