package com.example.leetcode.medium;

import java.util.*;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * Example 1:
 *
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 *
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 */
public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {

    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int res = 0;
        Queue<NodewithWeight> queue = new LinkedList<>();
        queue.add(new NodewithWeight(root,1));
        while (!queue.isEmpty()) {
            if (queue.size() == 1)
                queue.peek().weight = 1;
            int left = queue.peek().weight;
            int right = left;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                NodewithWeight nodewithWeight = queue.peek();
                TreeNode t = nodewithWeight.treeNode;
                right = nodewithWeight.weight;
                queue.poll();
                if (t.left != null)
                    queue.add(new NodewithWeight(t.left, right * 2));
                if (t.right != null)
                    queue.add(new NodewithWeight(t.right, right * 2 + 1));
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }


    public class NodewithWeight{
        TreeNode treeNode;
        Integer weight;

        public NodewithWeight(TreeNode treeNode, Integer weight) {
            this.treeNode = treeNode;
            this.weight = weight;
        }
    }


    /**
     * faster solution
     */
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
        find(root.left, 2*val, mappings, depth+1);
        find(root.right, 2*val+1, mappings, depth+1);
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
