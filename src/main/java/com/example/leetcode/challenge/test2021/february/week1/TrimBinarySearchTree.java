package com.example.leetcode.challenge.test2021.february.week1;


/**
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
 *
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,0,2], low = 1, high = 2
 * Output: [1,null,2]
 * Example 2:
 *
 *
 * Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * Output: [3,2,null,1]
 * Example 3:
 *
 * Input: root = [1], low = 1, high = 2
 * Output: [1]
 * Example 4:
 *
 * Input: root = [1,null,2], low = 1, high = 3
 * Output: [1,null,2]
 * Example 5:
 *
 * Input: root = [1,null,2], low = 2, high = 4
 * Output: [2]
 *
 */
public class TrimBinarySearchTree {
    public static void main(String[] args) {

    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null)
            return null;
        TreeNode left = trimBST(root.left,low,high);
        TreeNode right = trimBST(root.right,low,high);
        if(root.val >= low && root.val <= high){
            root.left = left;
            root.right = right;
            return root;
        } else {
            if(left != null)
                return left;
            else
                return right;
        }
    }

    public TreeNode trimBSTV1(TreeNode root, int low, int high) {
        if (root == null){
            return null;
        }
        if (root.val < low) {
            // if node.val is less than the low then its left part is also less then the low
            // so in answer this have to remove itself and its left part, anwser of this
            // node would be the answer of remaining right part
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            // its answer would be its left part answer so becoz we trimmed itself nd its
            // right part
            return trimBST(root.left, low, high);
        }

        // this is the case for node.val lies between the range, we would not trim anything
        // here
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;


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
