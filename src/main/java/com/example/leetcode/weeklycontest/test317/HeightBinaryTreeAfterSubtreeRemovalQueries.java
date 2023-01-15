package com.example.leetcode.weeklycontest.test317;

import com.example.leetcode.model.TreeNode;

import java.util.*;

/**
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
 *
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 *
 * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
 *
 * Note:
 *
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
 * Output: [2]
 * Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
 * The height of the tree is 2 (The path 1 -> 3 -> 2).
 * Example 2:
 *
 *
 * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
 * Output: [3,2,3,2]
 * Explanation: We have the following queries:
 * - Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
 * - Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
 * - Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
 * - Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 */
public class HeightBinaryTreeAfterSubtreeRemovalQueries {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/height-of-binary-tree-after-subtree-removal-queries/solution/java-by-tizzi-v9ye/
     */
    Map<Integer, Integer> height = new HashMap<>();
    Map<Integer, Integer> valToAns = new HashMap<>();
    public int[] treeQueries(TreeNode root, int[] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        getLength(root);
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> vals = new ArrayList<>();
        q.add(root);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int maxv = 0, maxv2 = -1;
            for (int i = 1; i <= size; i++) {
                TreeNode p = q.poll();
                vals.add(p.val);
                if (height.get(p.val) > maxv) {
                    maxv2 = maxv;
                    maxv = height.get(p.val);
                } else if (height.get(p.val) > maxv2) {
                    maxv2 = height.get(p.val);
                }
                if (p.left != null) q.add(p.left);
                if (p.right != null) q.add(p.right);
            }
            for (int i = 0; i < size; i++){
                int val = vals.get(i);
                if (height.get(val) == maxv) valToAns.put(val, maxv2 + step);
                else valToAns.put(val, maxv + step);
                if (size == 1) valToAns.put(val, valToAns.get(val) - 1); //特殊情况
            }
            vals.clear();
            step++;
        }
        for (int i = 0; i < n; i++) ans[i] = valToAns.get(queries[i]);
        return ans;
    }
    int getLength(TreeNode root) {
        if (root == null) return -1;
        int l = getLength(root.left);
        int r = getLength(root.right);
        int len = Math.max(l, r) + 1;
        height.put(root.val, len);
        return len;
    }

}
