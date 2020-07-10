package com.example.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

    }
    Map<Integer,List<Integer>> res;
    public List<List<Integer>> levelOrder(TreeNode root) {
        res = new HashMap<>();
        helper(root,0);
        return res.values().stream().collect(Collectors.toList());
    }

    public void helper(TreeNode node,int level){
        if(node == null)
            return;
        if(res.containsKey(level)){
            res.get(level).add(node.val);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            res.put(level,list);
        }
        helper(node.left,level+1);
        helper(node.right,level+1);
    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderV1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode node, int h) {
        if (node == null) return;
        if (h >= res.size()) res.add(new ArrayList<>());

        res.get(h).add(node.val);
        helper(res, node.left, h+1);
        helper(res, node.right, h+1);

    }


    /**
     * less memory
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderV2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int currSize = q.size();
            List<Integer> currentList = new ArrayList<>();
            for (int i = 0; i < currSize; i++) {
                TreeNode currNode = q.poll();
                currentList.add(currNode.val);
                if (currNode.left != null) {
                    q.add(currNode.left);
                }
                if (currNode.right != null) {
                    q.add(currNode.right);
                }
            }
            res.add(currentList);
        }
        return res;
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
