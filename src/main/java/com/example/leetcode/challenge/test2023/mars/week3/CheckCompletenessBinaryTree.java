package com.example.leetcode.challenge.test2023.mars.week3;

import com.example.leetcode.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, determine if it is a complete binary tree.
 *
 * In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 */
public class CheckCompletenessBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean reachedEnd = false;
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            if(reachedEnd && cur != null){
                return false;
            }
            if(cur == null){
                reachedEnd = true;
                continue;
            }
            q.offer(cur.left);
            q.offer(cur.right);
        }
        return true;
    }
}
