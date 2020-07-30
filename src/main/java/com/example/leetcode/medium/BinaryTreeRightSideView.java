package com.example.leetcode.medium;

import java.util.*;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,2,3,null,5,null,4};
        BinaryTreeRightSideView binaryTreeRightSideView = new BinaryTreeRightSideView();
        TreeNode root = binaryTreeRightSideView.convert(array);
        binaryTreeRightSideView.rightSideView(root);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null)
            return res;
        else{
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int stackSize;
            while (!queue.isEmpty()) {
                stackSize = queue.size();
                System.out.println(stackSize);
                res.add(queue.peek().val);
                while (stackSize > 0) {
                    TreeNode node = queue.poll();
                    if (node.right != null)
                        queue.offer(node.right);
                    if (node.left != null)
                        queue.offer(node.left);
                    stackSize--;
                }
            }
        }
        return res;
    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public List<Integer> rightSideViewV1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        rightSideView(root, res, 1);
        return res;
    }

    public void rightSideView(TreeNode root, List<Integer> res, int level) {
        if(root == null){
            return;
        }
        if(level > res.size()){
            res.add(root.val);
        }
        rightSideView(root.right, res, level + 1);
        rightSideView(root.left, res, level + 1);
    }

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        if(root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(i == 0){
                    res.add(node.val);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
            }
        }

        return res;
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
