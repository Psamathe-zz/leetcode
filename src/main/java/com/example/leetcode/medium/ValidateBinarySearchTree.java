package com.example.leetcode.medium;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * [10,5,15,null,null,6,20]
 * [-2147483648,null,2147483647]
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{-2147483648,-2147483648};
        ValidateBinarySearchTree validateBinarySearchTree = new ValidateBinarySearchTree();
        TreeNode root = validateBinarySearchTree.convert(array);
        boolean result = validateBinarySearchTree.isValidBST(root);
        System.out.println(result);
    }
    boolean result;
    public boolean isValidBST(TreeNode root) {
        result = true;
        if(root == null)
            return true;
        else {
            if(root.left != null)
                help(root.left, Integer.MIN_VALUE,root.val,root.val == Integer.MIN_VALUE?false:true,false);
            if(root.right != null)
                help(root.right, root.val, Integer.MAX_VALUE,false,root.val == Integer.MAX_VALUE?false:true);
        }
        return result;
    }

    public void help(TreeNode node,int min,int max,boolean minInfi,boolean maxInfi){
        if(!result)
            return;
        if(node.val >= max || node.val <= min ) {

            if((minInfi && node.val == Integer.MIN_VALUE) || (maxInfi && node.val == Integer.MAX_VALUE)) {

            } else {
                result = false;
            }
        }
        if(node.left != null)
            help(node.left, min,node.val,minInfi,false);
        if(node.right != null)
            help(node.right, node.val, max,false,maxInfi);
    }


    /**
     * faster solution
     * @param root
     * @param max
     * @param min
     * @return
     */
    public boolean helper(TreeNode root,long max,long min){

        if(root==null)
            return true ;
        if(root.val>=max||root.val<=min)
            return false;

        boolean a = helper(root.right,max,root.val) ;
        boolean b = helper(root.left,root.val,min) ;
        return a&&b ;
    }
    public boolean isValidBSTV1(TreeNode root) {

        long max = (long)1e10 ;
        long min = (long)-1e10 ;
        return helper(root,max,min) ;
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
