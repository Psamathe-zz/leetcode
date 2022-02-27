package com.example.leetcode.challenge.test2022.january;

import com.example.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 *
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 */
public class AllElementsInTwoBinarySearchTrees {
    public static void main(String[] args) {

    }
    List<Integer> res;
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        res = new ArrayList<>();
        helper(root1);
        helper(root2);
        Collections.sort(res);
        return res;
    }
    public void helper(TreeNode node) {
        if(node == null)
            return;
        helper(node.left);
        res.add(node.val);
        helper(node.right);
    }
}
