package com.example.leetcode.easy;

/**
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
 *
 * Example :
 *
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 * Note:
 *
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's value is different.
 * This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 * [90,69,null,49,89,null,52,null,null,null,null]
 */
public class MinimumDistanceBetweenBSTNodes {
    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{90,69,null,49,89,null,52,null,null,null,null};
        MinimumDistanceBetweenBSTNodes minimumDistanceBetweenBSTNodes = new MinimumDistanceBetweenBSTNodes();
        TreeNode treeNode = minimumDistanceBetweenBSTNodes.convert(arrays);
        int result = minimumDistanceBetweenBSTNodes.minDiffInBST(treeNode);
        System.out.println(result);
    }
    int result = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {

        help(root.left,root.val);
        help(root.right,root.val);
        return result;
    }

    public void help(TreeNode root,int pre) {
        if (root == null)
            return;

        int val = Math.abs(root.val - pre);
        if(val != 0 && val < result){
            result = val;
        }
        if(pre < root.val)
            help(root.left,pre);
        else
            help(root.right,pre);

        help(root.left,root.val);
        help(root.right,root.val);
    }

    /**
     * less memory
     */
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;
    public int minDiffInBSTV2(TreeNode root) {
        int res = Integer.MAX_VALUE;
        inOrderBST(root);
        return minDiff;
    }

    public void inOrderBST(TreeNode root){
        if (root == null) return;
        inOrderBST(root.left);
        if (prev != null) minDiff = Math.min(minDiff,root.val - prev.val);
        prev = root;
        inOrderBST(root.right);
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
