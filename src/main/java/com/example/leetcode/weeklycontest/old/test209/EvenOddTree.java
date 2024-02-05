package com.example.leetcode.weeklycontest.old.test209;


import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree is named Even-Odd if it meets the following conditions:
 *
 * The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 * For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 * For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * Output: true
 * Explanation: The node values on each level are:
 * Level 0: [1]
 * Level 1: [10,4]
 * Level 2: [3,7,9]
 * Level 3: [12,8,6,2]
 * Since levels 0 and 2 are all odd and increasing, and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
 * Example 2:
 *
 *
 *
 * Input: root = [5,4,2,3,3,7]
 * Output: false
 * Explanation: The node values on each level are:
 * Level 0: [5]
 * Level 1: [4,2]
 * Level 2: [3,3,7]
 * Node values in the level 2 must be in strictly increasing order, so the tree is not Even-Odd.
 * Example 3:
 *
 *
 *
 * Input: root = [5,9,1,3,5,7]
 * Output: false
 * Explanation: Node values in the level 1 should be even integers.
 * Example 4:
 *
 * Input: root = [1]
 * Output: true
 * Example 5:
 *
 * Input: root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
 * Output: true
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 106
 */
public class EvenOddTree {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,9,1,3,5,7};
        EvenOddTree evenOddTree = new EvenOddTree();
        TreeNode root = evenOddTree.convert(array);
        boolean res = evenOddTree.isEvenOddTree(root);
        System.out.println(res);
    }

    public boolean isEvenOddTree(TreeNode root) {
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        int count;
        TreeNode currentNode;
        int pre;
        int currentVal;
        while (size > 0){
            count = size;
            if(level % 2 == 0)
                pre = 0;
            else
                pre = Integer.MAX_VALUE;
            while (count > 0){
                currentNode = queue.poll();
                currentVal = currentNode.val;
                if(level % 2 == 0){
                    if(currentVal % 2 == 0 || currentVal <= pre)
                        return false;
                } else {
                    if(currentVal % 2 == 1 || currentVal >= pre)
                        return false;
                }
                if(currentNode.left != null)
                    queue.add(currentNode.left);
                if(currentNode.right != null)
                    queue.add(currentNode.right);
                pre = currentNode.val;
                count--;
            }
            level++;
            size = queue.size();
        }
        return true;
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
