package com.example.leetcode.challenge.test2022.december;

import com.example.leetcode.model.TreeNode;

import java.util.Arrays;

/**
 * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 *
 * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Example 2:
 *
 *
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 *
 */
public class MaximumDifferenceBetweenNodeAncestor {
    public static void main(String[] args) {

    }

    int result = 0;
    public int maxAncestorDiff(TreeNode root) {
        helper(root);
        return result;
    }

    public int[] helper(TreeNode node){
        int[] res = new int[2];
        Arrays.fill(res,node.val);
        int[] left;
        int[] right;
        if(node.left != null){
            left = helper(node.left);
            result = Math.max(result, Math.abs(node.val - left[0]));
            result = Math.max(result, Math.abs(node.val - left[1]));
            res[0] = Math.min(res[0],left[0]);
            res[1] = Math.max(res[1],left[1]);
        }
        if(node.right != null){
            right = helper(node.right);
            result = Math.max(result, Math.abs(node.val - right[0]));
            result = Math.max(result, Math.abs(node.val - right[1]));
            res[0] = Math.min(res[0],right[0]);
            res[1] = Math.max(res[1],right[1]);
        }
        res[0] = Math.min(res[0],node.val);
        res[1] = Math.max(res[1],node.val);
        return res;
    }
}
