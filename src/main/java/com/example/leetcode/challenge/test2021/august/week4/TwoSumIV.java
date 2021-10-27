package com.example.leetcode.challenge.test2021.august.week4;

import com.example.leetcode.model.TreeNode;

import java.util.*;

/**
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 * Example 3:
 *
 * Input: root = [2,1,3], k = 4
 * Output: true
 * Example 4:
 *
 * Input: root = [2,1,3], k = 1
 * Output: false
 * Example 5:
 *
 * Input: root = [2,1,3], k = 3
 * Output: true
 */
public class TwoSumIV {
    public static void main(String[] args) {

    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }

        int toFind;
        for (Integer val : list){
            toFind = k - val;
            if(val != toFind && list.contains(toFind))
                return true;
        }
        return false;
    }


    /**
     * faster solution
     * @param root
     * @param list
     */
    List<Integer> list;
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
    public boolean findTargetV1(TreeNode root, int k) {
        list = new ArrayList<>();
        inorder(root);
        int l = 0, r = list.size() - 1;
        int sum = 0;
        while (l < r) {
            sum = list.get(l) + list.get(r);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }
}
