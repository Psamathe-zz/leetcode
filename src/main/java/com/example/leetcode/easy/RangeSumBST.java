package com.example.leetcode.easy;

/**
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
 *
 * The binary search tree is guaranteed to have unique values.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 * Example 2:
 *
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 *
 *
 * Note:
 *
 * The number of nodes in the tree is at most 10000.
 * The final answer is guaranteed to be less than 2^31.
 */
public class RangeSumBST {

    public static void main(String[] args) {
        int[] treeArr = new int[]{10,5,15,3,7,0,18};
        RangeSumBST rangeSumBST = new RangeSumBST();
        TreeNode root = rangeSumBST.createTree(treeArr);
        int result = rangeSumBST.rangeSumBST(root,7,15);
        System.out.println(result);
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        int result = 0;
        if(root == null)
            return 0;
        if(root.val >= L && root.val <= R)
            result += root.val;
        result += rangeSumBST(root.left,L,R);
        result += rangeSumBST(root.right,L,R);
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode createTree (int[] treeArr) {
        TreeNode[] tree = new TreeNode[treeArr.length];
        for (int i = 0; i < treeArr.length; i++) {
            if (treeArr[i] == 0) {
                tree[i] = null;
                continue;
            }
            tree[i] = new TreeNode(treeArr[i]);
        }
        int pos = 0;
        for (int i = 0; i < treeArr.length && pos < treeArr.length-1; i++) {
            if (tree[i] != null) {
                tree[i].left = tree[++pos];
                if (pos < treeArr.length-1) {
                    tree[i].right = tree[++pos];
                }
            }
        }
        return tree[0];
    }
}
