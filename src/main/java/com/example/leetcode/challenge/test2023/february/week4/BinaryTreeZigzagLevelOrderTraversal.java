package com.example.leetcode.challenge.test2023.february.week4;

import com.example.leetcode.model.TreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode node;
        boolean order = true;
        int length;
        while (!queue.isEmpty()) {
            length = queue.size();
            List<Integer> list = new ArrayList<>();
            while (length > 0) {
                node = queue.poll();
                list.add(node.val);

                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);

                length--;
            }
            if(!order)
                Collections.reverse(list);
            res.add(list);
            order = !order;
        }
        return res;
    }
}
