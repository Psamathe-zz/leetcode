package com.example.leetcode.medium;

public class SumRootLeafNumbers {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{4,9,0,5,1};
        SumRootLeafNumbers sumRootLeafNumbers = new SumRootLeafNumbers();
        TreeNode root = sumRootLeafNumbers.convert(arr);
        int result = sumRootLeafNumbers.sumNumbers(root);
        System.out.println(result);
    }

    int result = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return result;
        else {
            roodToLeaf(root, 0);
            return result;
        }
    }

    public void roodToLeaf(TreeNode node,int value){
        if(node.left == null && node.right == null){
            result += value * 10 + node.val;
        } else {
            if(node.left != null)
                roodToLeaf(node.left, value * 10  + node.val);
            if(node.right != null)
                roodToLeaf(node.right, value * 10  + node.val);
        }
    }


    /**
     * less memory
     * @param root
     * @return
     */
    public int sumNumbersV1(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int sum){
        if(root == null){
            return 0;
        }

        int temp = sum * 10 + root.val;
        if(root.left == null && root.right == null){
            return temp;
        }

        return helper(root.left, temp) + helper(root.right, temp);
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
