package com.example.leetcode.weeklycontest.old.test199;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
 *
 * Return the number of good leaf node pairs in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,4], distance = 3
 * Output: 1
 * Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,5,6,7], distance = 3
 * Output: 2
 * Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.
 * Example 3:
 *
 * Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 * Output: 1
 * Explanation: The only good pair is [2,5].
 * Example 4:
 *
 * Input: root = [100], distance = 1
 * Output: 0
 * Example 5:
 *
 * Input: root = [1,1,1], distance = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 2^10].
 * Each node's value is between [1, 100].
 * 1 <= distance <= 10
 */
public class NumberGoodLeafNodesPairs {
    public static void main(String[] args) {

    }

    int count=0;
    public int countPairs(TreeNode root, int distance) {
        help(root,distance,0);
        return count;
    }
    List<Integer> help(TreeNode root, int distance, int level){
        if(root==null){
            return new ArrayList<>();
        }
        if(root.left==null&&root.right==null){
            List<Integer> list=new ArrayList<>();
            list.add(level);
            return list;
        }
        List<Integer> left=help(root.left, distance,level+1);
        List<Integer> right=help(root.right,distance,level+1);
        for(int l : left){
            for(int r : right){
                if((l-level)+(r-level)<=distance){
                    count++;
                }
            }
        }
        left.addAll(right);
        return left;
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
