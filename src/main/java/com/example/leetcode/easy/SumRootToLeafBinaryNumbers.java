package com.example.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeafBinaryNumbers {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,1};
        SumRootToLeafBinaryNumbers sumRootToLeafBinaryNumbers = new SumRootToLeafBinaryNumbers();
        TreeNode root = sumRootToLeafBinaryNumbers.convert(array);
        int result = sumRootToLeafBinaryNumbers.sumRootToLeaf(root);
        System.out.println(result);
    }

    List<String> result ;
    public int sumRootToLeaf(TreeNode root) {
        int value = 0;
        result = new ArrayList<>();
        help(root,"");
        for(String str:result){
            value += Integer.parseInt(str,2);
        }
        return value;
    }
    public void help(TreeNode node,String pre){
        if(node== null)
            return;
        if(node.left == null && node.right == null) {
            result.add(pre + node.val);
        }
        else {
            help(node.left,pre+node.val);
            help(node.right,pre+node.val);
        }
    }

    /**
     * faster solution
     * @param root
     * @return
     */
    public int sumRootToLeafV1(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode node, int sum){
        if(node.left == null && node.right == null){
            return sum * 2 + node.val;
        }

        int left = node.left != null ? sum(node.left, sum * 2 + node.val) : 0;
        int right = node.right != null ? sum(node.right, sum * 2 + node.val) : 0;

        return left + right;
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
