package com.example.leetcode.challenge.test2022.february;

import com.example.leetcode.model.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 *
 *
 * Input: root = [1,3,null,5,3]
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 *
 *
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 */
public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {

    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueValue = new LinkedList<>();
        queueNode.add(root);
        queueValue.add(0);
        int size;
        int val;
        int start;
        int end;
        int res = 1;
        TreeNode node;
        while (!queueNode.isEmpty()) {
            size = queueNode.size();
            start = -1;
            end = -1;
            while (size > 0) {
                node = queueNode.poll();
                val = queueValue.poll();
                System.out.println(val);
                if(start == -1) {
                    start = val;
                } else
                    end = val;
                if(node.left != null) {
                    queueNode.add(node.left);
                    queueValue.add(val * 2);
                }
                if(node.right != null) {
                    queueNode.add(node.right);
                    queueValue.add(val * 2 + 1);
                }
                size--;
            }
            System.out.println("---------");
            System.out.println(start);
            System.out.println(end);
            System.out.println("---------");
            if(end == 0)
                res = Math.max(res, 1);
            else
                res = Math.max(res, Math.abs(end - start) + 1);
        }

        return res;
    }

    int max = 0;
    public int widthOfBinaryTreeV1(TreeNode root) {
        // left -> 2*upper value
        // right -> 2*upper + 1
        // max difference -> restlt
        find(root, 1, new HashMap(), 0);
        return max == 0 ? 1 : max;
    }

    private void find(TreeNode root, int val, Map<Integer, Integer> mappings, int depth) {
        if (root == null) {
            return;
        }
        if (! mappings.containsKey(depth)) {
            mappings.put(depth, val);
        } else {
            max = Math.max(max, Math.abs(mappings.get(depth) - val) + 1);
        }
        System.out.println(2 * val);
        find(root.left, 2*val, mappings, depth+1);
        find(root.right, 2*val+1, mappings, depth+1);
    }
}
