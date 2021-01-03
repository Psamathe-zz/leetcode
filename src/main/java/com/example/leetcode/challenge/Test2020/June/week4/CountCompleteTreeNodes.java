package com.example.leetcode.challenge.June.week4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 *
 * https://www.cnblogs.com/grandyang/p/4567827.html
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        Integer[] arr= new Integer[]{1,2,3,4,5,6};

        CountCompleteTreeNodes countCompleteTreeNodes = new CountCompleteTreeNodes();
        TreeNode root = countCompleteTreeNodes.convert(arr);
        int result = countCompleteTreeNodes.countNodesV2(root);
        System.out.printf("", result);
    }
    public int countNodes(TreeNode root) {
        int hLeft = countLeft(root);
        int hRight = countRight(root);
        if (hLeft == hRight)
            return (int) (Math.pow(2, hLeft) - 1);
        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    public int countLeft(TreeNode node){
        int count = 0;
        while (node != null){
            count++;
            node = node.left;
        }
        return count;
    }

    public int countRight(TreeNode node){
        int count = 0;
        while (node != null){
            count++;
            node = node.right;
        }
        return count;
    }


    /**
     * less memory
     */
    HashMap<TreeNode,Integer> map = new HashMap<TreeNode,Integer>();
    Queue<TreeNode> st = new LinkedList<TreeNode>();
    int count =0;
    public int countNodesV2(TreeNode root) {
        dfs(root);
        while(!st.isEmpty()){
            TreeNode node = st.peek();
            st.poll();
            if(node.left==null && node.right ==null){
                count++;
                map.put(node,1);
            }
            else if(node.left!=null && node.right ==null && map.containsKey(node.left)){
                if(map.get(node.left)==1){
                    count++;
                    map.put(node, map.get(node.left)+1);
                }
            }
            else{
                if(map.containsKey(node.left) && map.containsKey(node.right)){
                    if(map.get(node.left)==map.get(node.right)+1 || map.get(node.left)==map.get(node.right)){
                        count++;
                        map.put(node, map.get(node.left)+1);
                    }
                }
            }
        }
        return count;
    }

    public void dfs(TreeNode root){
        if(root!=null){
            if(root.left!=null)
                dfs(root.left);
            if(root.right!=null)
                dfs(root.right);
            st.add(root);
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
