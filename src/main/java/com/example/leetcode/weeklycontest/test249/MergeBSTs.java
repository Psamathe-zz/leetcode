package com.example.leetcode.weeklycontest.test249;

import com.example.leetcode.model.TreeNode;

import java.util.*;

/**
 * You are given n BST (binary search tree) root nodes for n separate BSTs stored in an array trees (0-indexed). Each BST in trees has at most 3 nodes, and no two roots have the same value. In one operation, you can:
 *
 * Select two distinct indices i and j such that the value stored at one of the leaves of trees[i] is equal to the root value of trees[j].
 * Replace the leaf node in trees[i] with trees[j].
 * Remove trees[j] from trees.
 * Return the root of the resulting BST if it is possible to form a valid BST after performing n - 1 operations, or null if it is impossible to create a valid BST.
 *
 * A BST (binary search tree) is a binary tree where each node satisfies the following property:
 *
 * Every node in the node's left subtree has a value strictly less than the node's value.
 * Every node in the node's right subtree has a value strictly greater than the node's value.
 * A leaf is a node that has no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: trees = [[2,1],[3,2,5],[5,4]]
 * Output: [3,2,5,1,null,4]
 * Explanation:
 * In the first operation, pick i=1 and j=0, and merge trees[0] into trees[1].
 * Delete trees[0], so trees = [[3,2,5,1],[5,4]].
 *
 * In the second operation, pick i=0 and j=1, and merge trees[1] into trees[0].
 * Delete trees[1], so trees = [[3,2,5,1,null,4]].
 *
 * The resulting tree, shown above, is a valid BST, so return its root.
 * Example 2:
 *
 *
 * Input: trees = [[5,3,8],[3,2,6]]
 * Output: []
 * Explanation:
 * Pick i=0 and j=1 and merge trees[1] into trees[0].
 * Delete trees[1], so trees = [[5,3,8,2,6]].
 *
 * The resulting tree is shown above. This is the only valid operation that can be performed, but the resulting tree is not a valid BST, so return null.
 * Example 3:
 *
 *
 * Input: trees = [[5,4],[3]]
 * Output: []
 * Explanation: It is impossible to perform any operations.
 * Example 4:
 *
 *
 * Input: trees = [[2,1,3]]
 * Output: [2,1,3]
 * Explanation: There is only one tree, and it is already a valid BST, so return its root.
 *
 *
 * Constraints:
 *
 * n == trees.length
 * 1 <= n <= 5 * 104
 * The number of nodes in each tree is in the range [1, 3].
 * No two roots of trees have the same value.
 * All the trees in the input are valid BSTs.
 * 1 <= TreeNode.val <= 5 * 104.
 */
public class MergeBSTs {
    public static void main(String[] args) {

    }

    public TreeNode canMerge(List<TreeNode> trees) {
        //获得最终根节点
        TreeNode root = getRoot(trees);
        if(root == null) return null;
        Map<Integer, TreeNode> rootValMap = new HashMap<>();
        for(TreeNode tree : trees) {
            rootValMap.put(tree.val, tree);
        }
        //BFS模板
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                TreeNode parent = q.poll();
                //当前节点的left, 和其他树链接
                if(parent.left != null && rootValMap.containsKey(parent.left.val)) {
                    parent.left = rootValMap.get(parent.left.val);
                    q.offer(parent.left);
                    rootValMap.remove(parent.left.val);
                }
                //当前节点的right, 和其他树链接
                if(parent.right != null && rootValMap.containsKey(parent.right.val)) {
                    parent.right = rootValMap.get(parent.right.val);
                    q.offer(parent.right);
                    rootValMap.remove(parent.right.val);
                }
            }
        }
        if(checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) && rootValMap.size() == 1) {
            return root;
        }
        return null;
    }

    //根据val出现次数，对比输入参数，得出最终解的根节点
    private TreeNode getRoot(List<TreeNode> trees) {
        Map<Integer, Integer> countMap = new HashMap<>();
        //统计全部节点val出现次数
        for(TreeNode tree : trees) {
            countMap.put(tree.val, countMap.getOrDefault(tree.val, 0) + 1);
            if(tree.left != null) {
                countMap.put(tree.left.val, countMap.getOrDefault(tree.left.val, 0) + 1);
            }
            if(tree.right != null) {
                countMap.put(tree.right.val, countMap.getOrDefault(tree.right.val, 0) + 1);
            }
        }
        //val仅出现1次，且在为根的，即为我们要找的合并后树的根
        TreeNode ret = null;
        for(TreeNode tree : trees) {
            if(countMap.get(tree.val) == 1) {
                if(ret == null) {
                    ret = tree;
                } else {
                    //如果有两个根满足这个条件，说明无解
                    return null;
                }
            }
        }
        return ret;
    }
    //模板，参考leetcode 98
    private boolean checkBST(TreeNode root, Integer min, Integer max) {
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return checkBST(root.left, min, root.val) && checkBST(root.right, root.val, max);
    }


}
