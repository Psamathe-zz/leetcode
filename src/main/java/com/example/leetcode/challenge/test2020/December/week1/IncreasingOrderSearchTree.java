package com.example.leetcode.challenge.test2020.December.week1;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * Example 2:
 *
 *
 * Input: root = [5,1,7]
 * Output: [1,null,5,null,7]
 *
 * [2,1,4,null,null,3]
 *
 * Constraints:
 *
 * The number of nodes in the given tree will be in the range [1, 100].
 * 0 <= Node.val <= 1000
 */
public class IncreasingOrderSearchTree {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{2,1,4,null,null,3};
        IncreasingOrderSearchTree increasingOrderSearchTree = new IncreasingOrderSearchTree();
        TreeNode root = increasingOrderSearchTree.convert(array);
        increasingOrderSearchTree.increasingBST(root);
    }

    Queue<TreeNode> queue;
    public TreeNode increasingBST(TreeNode root) {
        queue = new LinkedList<>();
        helper(root);
        TreeNode nodeRes = queue.poll();
        TreeNode cur = nodeRes;
        TreeNode temp;
        while (!queue.isEmpty()){
            temp = queue.poll();
            cur.left = null;
            cur.right = temp;
            cur = temp;
        }
        cur.left = null;
        return nodeRes;
    }

    /**
     * other option
     */
    TreeNode cur;

    public TreeNode increasingBSTV1(TreeNode root)
    {
        TreeNode ans = new TreeNode(0);
        cur = ans;
        inorder(root);
        return ans.right;
    }

    public void inorder(TreeNode node)
    {
        if(node == null) return;
        inorder(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inorder(node.right);
    }

    public void helper(TreeNode node){
        if(node == null)
            return;
        helper(node.left);
        queue.add(node);
        helper(node.right);
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
