package com.example.leetcode.medium;

import java.util.ArrayDeque;
import java.util.HashMap;

public class DeepestLeavesSum {
    public static void main(String[] args) {

    }

    int sum;
    int currentMaxLevel;
    public int deepestLeavesSum(TreeNode root) {

        help(root,0);
        return sum;
    }

    public void help(TreeNode node,int level){
        if(node.left == null && node.right ==null){
            if(level > currentMaxLevel){
                sum = node.val;
                currentMaxLevel = level;
            } else if(level == currentMaxLevel){
                sum += node.val;
            }
        }
        if (node.left != null){
            help(node.left,level + 1);
        }
        if (node.right != null){
            help(node.right,level + 1);
        }

    }


    /**
     * less memory
     * @param root
     * @return
     */
    public int deepestLeavesSumV1(TreeNode root) {

        int l = 0;
        if(root == null){
            return 0;
        }
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        ArrayDeque<Integer> level = new ArrayDeque<>();
        level.add(l);
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeNode temp = new TreeNode();
        while(!q.isEmpty()){
            temp = q.poll();
            l = level.poll();
            map.put(l,temp.val+map.getOrDefault(l,0));
            l++;
            if(temp.left != null){
                q.add(temp.left);
                level.add(l);
            }
            if(temp.right != null){
                q.add(temp.right);
                level.add(l);
            }
        }
        return map.get(l-1);
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
