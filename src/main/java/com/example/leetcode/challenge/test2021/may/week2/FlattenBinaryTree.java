package com.example.leetcode.challenge.test2021.may.week2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
public class FlattenBinaryTree {
    public static void main(String[] args) {

    }

    Queue<TreeNode> queue;
    public void flatten(TreeNode root) {
        TreeNode p = null;
        queue = new LinkedList<>();
        helper(root);
        do {
            p = queue.poll();
            if(!queue.isEmpty())
                p.right = queue.peek();
        }while (!queue.isEmpty());
    }

    public void helper(TreeNode node){
        if(node == null)
            return;
        queue.add(node);
        helper(node.left);
        helper(node.right);
    }

    public void flattenV0(TreeNode root) {
        if(root == null)
            return;
        while(root != null) {
            if(root.left != null) {
                TreeNode left = root.left;
                TreeNode temp = left;
                while(temp.right != null) {
                    temp = temp.right;
                }
                temp.right = root.right;
                root.right = left;
                root.left = null;
            }
            root = root.right;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
