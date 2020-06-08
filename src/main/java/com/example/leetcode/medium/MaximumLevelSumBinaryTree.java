package com.example.leetcode.medium;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 *
 * Note:
 *
 * The number of nodes in the given tree is between 1 and 10^4.
 * -10^5 <= node.val <= 10^5
 */
public class MaximumLevelSumBinaryTree {
    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{1,7,0,7,-8,null,null};
        MaximumLevelSumBinaryTree maximumLevelSumBinaryTree = new MaximumLevelSumBinaryTree();
        TreeNode root = maximumLevelSumBinaryTree.convert(arrays);
        int result = maximumLevelSumBinaryTree.maxLevelSum(root);
        System.out.println(result);
    }

    Map<Integer,Integer> map;
    public int maxLevelSum(TreeNode root) {
        map = new HashMap<>();
        dfs(root,1);
        int result  = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(e->e.getKey()).findFirst().orElse(Integer.MIN_VALUE);
        return  result;
    }

    public void dfs(TreeNode node,int level){
        if(node == null)
            return;
        else {
            int value = node.val;
            int temp = map.getOrDefault(level,0) + value;
            map.put(level,temp);
        }
        dfs(node.left,level + 1);
        dfs(node.right,level + 1);

    }


    /**
     * faster solution
     */
    int[] sums = new int[1000];
    int e = 0;
    public int maxLevelSumV1(TreeNode root) {
        maxLevel(root, 1);
        int max = Integer.MIN_VALUE, r = -1;
        for(int i = 1; i <= e; i++){
            if(sums[i] > max){
                max = sums[i];
                r = i;
            }
        }
        return r;
    }

    public void maxLevel(TreeNode current, int level){
        if(current != null){
            sums[level]+= current.val;
            e = Math.max(e, level);
            maxLevel(current.left, level + 1);
            maxLevel(current.right, level + 1);
        }
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
