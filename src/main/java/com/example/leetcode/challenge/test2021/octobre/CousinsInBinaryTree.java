package com.example.leetcode.challenge.test2021.octobre;

import com.example.leetcode.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 * Example 3:
 *
 *
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 *
 * [1,3,2,null,5,4]
 * 3
 * 2
 */
public class CousinsInBinaryTree {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,3,2,null,5,4};
        TreeNode treeNode = TreeNode.convert(array);
        CousinsInBinaryTree cousinsInBinaryTree = new CousinsInBinaryTree();
        cousinsInBinaryTree.isCousins(treeNode, 3, 2);
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null)
            return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        boolean findX;
        boolean findY;
        while (!queue.isEmpty()) {
            size = queue.size();
            findX = false;
            findY = false;
            while (size > 0){
                TreeNode node = queue.poll();
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
                if(node.left != null && node.right != null &&((node.left.val == x && node.right.val == y) || (node.left.val == y && node.right.val == x)))
                    return false;
                size--;
                if(x == node.val)
                    findX = true;
                if(y == node.val)
                    findY = true;
            }
            if( findX && findY)
                return true;
        }
        return false;
    }
}
