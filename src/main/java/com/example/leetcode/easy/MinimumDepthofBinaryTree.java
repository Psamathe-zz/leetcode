package com.example.leetcode.easy;

public class MinimumDepthofBinaryTree {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3,9,20,null,null,15,7};
        MinimumDepthofBinaryTree minimumDepthofBinaryTree = new MinimumDepthofBinaryTree();
        TreeNode treeNode = minimumDepthofBinaryTree.convert(arr);
        int result = minimumDepthofBinaryTree.minDepth(treeNode);
        System.out.println(result);
    }

    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        else if(root.left == null && root.right == null)
            return 1;
        else if(root.left == null)
            return minDepth(root.right) + 1;
        else if(root.right == null)
            return minDepth(root.left) + 1;
        else
            return Math.min(minDepth(root.right),minDepth(root.left)) + 1;
    }

    public int findleaf(TreeNode nood){
        if(nood == null)
            return 0;
        else if(minDepth(nood.left) > minDepth(nood.right))
            return minDepth(nood.right) + 1;
        else
            return minDepth(nood.left) + 1;
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
