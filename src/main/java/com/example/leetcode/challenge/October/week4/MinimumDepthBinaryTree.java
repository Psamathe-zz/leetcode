package com.example.leetcode.challenge.October.week4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 *
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 */
public class MinimumDepthBinaryTree {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{2,null,3,null,4,null,5,null,6};
        MinimumDepthBinaryTree minimumDepthBinaryTree = new MinimumDepthBinaryTree();
        TreeNode root = minimumDepthBinaryTree.convert(array);
        int res = minimumDepthBinaryTree.minDepth(root);
        System.out.println(res);
    }

    int res = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        else if(root.left == null && root.right == null){
            return 1;
        }
        help(root,0);
        return res;
    }

    public void help(TreeNode root,int deep){
        if(deep >= res){
            return;
        }
        if(root.left == null && root.right == null){
            res = Math.min(res,deep + 1);
        } else {
            if(root.left != null){
                help(root.left, deep + 1);
            }
            if(root.right != null){
                help(root.right, deep + 1);
            }
        }
    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public int minDepthV1(TreeNode root) {
        if(root == null){return 0;}
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root);

        int level =0;
        while(!q1.isEmpty()){
            int size = q1.size();
            level++;
            while(size-->0){

                TreeNode c = q1.poll();

                if(c.left == null && c.right == null){
                    return level;
                }
                if(c.left !=null){
                    q1.add(c.left);
                }
                if(c.right !=null) {
                    q1.add(c.right);
                }
            }
        }
        return level;
    }


    public boolean isLeaf(TreeNode root){
        return (root.left ==null) &&(root.right == null);
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
