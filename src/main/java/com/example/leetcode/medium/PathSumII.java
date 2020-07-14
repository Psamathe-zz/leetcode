package com.example.leetcode.medium;


import com.example.leetcode.easy.MinimumDepthofBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * [5,4,8,11,null,13,4,7,2,null,null,5,1]
 * 22
 */
public class PathSumII {
    public static void main(String[] args) {
        Integer[] arrs = new Integer[]{};
        PathSumII pathSumII = new PathSumII();
        TreeNode root = pathSumII.convert(arrs);
        pathSumII.pathSum(root,22);
    }

    List<List<Integer>> res;
    Stack<Integer> stack;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        stack = new Stack<>();
        if(root == null)
            return res;
        helper(root,0,sum);
        return res;
    }

    public void helper(TreeNode node,int sum,int target){
        if(node.left==null && node.right==null){
            if( sum + node.val == target) {
                List<Integer> list = new ArrayList<>(stack);
                list.add(node.val);
                res.add(list);
            }
            return;
        }

        stack.add(node.val);
        if(node.left != null) {
            helper(node.left, sum + node.val, target);
        }
        if(node.right != null) {
            helper(node.right, sum + node.val, target);
        }
        stack.pop();
    }


    /**
     * faster solution
     */
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> pathSumV1(TreeNode root, int sum) {
        isSum(root, sum, new ArrayList<>());
        return ans;
    }
    private void isSum(TreeNode node, int sum, List<Integer> someList) {
        if (node == null) return;

        sum -= node.val;
        someList.add(node.val);

        if (node.left == null && node.right == null && sum == 0) {
            List<Integer> list = new ArrayList<>();
            list.addAll(someList);
            ans.add(list);
        } else {
            isSum(node.left, sum, someList);
            isSum(node.right, sum, someList);
        }
        someList.remove(someList.size()-1);
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
