package com.example.leetcode.challenge.test2022.december;

import com.example.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 *
 *
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * Example 2:
 *
 *
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 */
public class LeafSimilarTrees {
    public static void main(String[] args) {

    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        helper(root1, list1);
        helper(root2, list2);
        if(list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if(!Objects.equals(list1.get(i), list2.get(i)))
                return false;
        }
        return true;
    }

    public void helper(TreeNode node, List<Integer> list) {
        if(node == null)
            return;
        if(node.left == null && node.right ==null)
            list.add(node.val);
        helper(node.left, list);
        helper(node.right, list);
    }
}
