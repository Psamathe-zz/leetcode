package com.example.leetcode.challenge.test2021.september.week1;

import com.example.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 */
public class UniqueBinarySearchTreesII {
    public static void main(String[] args) {
        UniqueBinarySearchTreesII uniqueBinarySearchTreesII = new UniqueBinarySearchTreesII();
        List<TreeNode> res = uniqueBinarySearchTreesII.generateTrees(1);
        System.out.println(res);
    }

    public List<TreeNode> generateTrees(int n) {
        return helper(1,n);
    }

    public List<TreeNode> helper(int start, int end){
        List<TreeNode> list = new ArrayList<>();
        if(start >= end) {
            if(start == end)
                list.add(new TreeNode(start));
            else
                list.add(null);
            return list;
        }
        List<TreeNode> left;
        List<TreeNode> right;
        for (int i = start; i <= end ; i++) {
            left = helper(start, i - 1);
            right = helper(i + 1, end);
            for (TreeNode leftNode : left){
                for(TreeNode rightNode: right){
                    list.add(new TreeNode(i,leftNode, rightNode));
                }
            }
        }
        return list;
    }
}
