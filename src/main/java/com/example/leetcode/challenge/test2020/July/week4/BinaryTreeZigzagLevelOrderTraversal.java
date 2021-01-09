package com.example.leetcode.challenge.test2020.July.week4;

import java.util.*;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * [1,2,3,4,null,null,5]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Integer[] arrs = new Integer[]{1,2,3,4,null,null,5};
        BinaryTreeZigzagLevelOrderTraversal binaryTreeZigzagLevelOrderTraversal = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode root = binaryTreeZigzagLevelOrderTraversal.convert(arrs);
        binaryTreeZigzagLevelOrderTraversal.zigzagLevelOrder(root);
    }
    Map<Integer,List<Integer>> map;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        map = new HashMap<>();
        helper(root,0);
        List<List<Integer>> res = new ArrayList<>();
        int index = 0;
        for (List<Integer> list : map.values()){
            if(index % 2 == 1)
                Collections.reverse(list);
            res.add(list);
            index++;
        }
        return res;
    }

    public void helper(TreeNode node,int level){
        if (node == null)
            return;
        List<Integer> list = map.getOrDefault(level,new ArrayList<>());
        list.add(node.val);
        map.put(level,list);
        helper(node.left,level + 1);
        helper(node.right,level + 1);
    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrderV1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)
            return result;
        explore(root, 0, result);
        return result;
    }
    public void explore(TreeNode root, int level, List<List<Integer>> result){
        if(root==null)
            return;
        if(result.size()==level){
            result.add(new ArrayList<Integer>());
        }
        if(level%2==0)
            result.get(level).add(root.val);
        else
            result.get(level).add(0, root.val);
        explore(root.left, level+1, result);
        explore(root.right, level+1, result);
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
