package com.example.leetcode.challenge.test2020.november.week2;


import java.util.Arrays;

/**
 * Given the root of a binary tree, find the maximum value V for which there exist different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.
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
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 5000].
 * 0 <= Node.val <= 105
 */
public class MaximumDifferenceBetweenNodeAncestor {
    public static void main(String[] args) {
        Integer[] arr =new Integer[]{8,3,10,1,6,null,14,null,null,4,7,13};
        MaximumDifferenceBetweenNodeAncestor maximumDifferenceBetweenNodeAncestor = new MaximumDifferenceBetweenNodeAncestor();
        TreeNode root = maximumDifferenceBetweenNodeAncestor.convert(arr);
        int res = maximumDifferenceBetweenNodeAncestor.maxAncestorDiff(root);
        System.out.println(res);
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


    /**
     * other option
     * @param root
     * @return
     */
    public int maxAncestorDiffV1(TreeNode root) {
        if (root == null){
            return 0;
        }
        result = 0;
        helper(root, root.val, root.val);
        return result;
    }
    void helper (TreeNode node, int curMax, int curMin) {
        if (node == null){
            return;
        }
        int possibleResult = Math.max(Math.abs(curMax - node.val), Math.abs(curMin - node.val));
        result = Math.max(result, possibleResult);
        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin,node.val);
        helper(node.left, curMax, curMin);
        helper(node.right, curMax, curMin);
        return;
    }

    public class Range{
        int max;
        int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
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
