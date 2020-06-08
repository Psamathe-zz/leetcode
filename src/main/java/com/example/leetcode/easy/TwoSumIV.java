package com.example.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class TwoSumIV {
    public static void main(String[] args) {

    }

    Set<Integer> set;
    public boolean findTarget(TreeNode root, int k) {
        set = new HashSet<>();
        return help(root,k);
    }

    public boolean help(TreeNode root, int k){
        if(root == null)
            return false;
        if(set.contains(k - root.val)) {
            return true;
        }else {
            set.add(root.val);
            return help(root.left,k)||help(root.right,k);
        }
    }


    /**
     * faster solution
     * @param root
     * @param k
     * @return
     */
    public boolean findTargetV1(TreeNode root, int k) {

        return traverse(root, root, k);

    }

    private boolean traverse (TreeNode root, TreeNode node, int k){
        if (node == null){
            return false;
        }

        //System.out.println("Traverse:"+node.val);
        boolean found=findNode(root, node.val, k-node.val);
        if (found) {
            return true;
        }
        else {
            return traverse(root,node.left,k) || traverse (root,node.right,k);
        }
    }


    private boolean findNode(TreeNode node, int v, int val){

        if (node == null){
            return false;
        }
        // System.out.println("Find: val:"+val+"node value:"+node.val);
        if (v == val){
            return false;
        }
        if (node.val==val ){
            return true;
        }
        if (node.val >  val){
            return findNode(node.left,v, val);
        }
        else {
            return findNode(node.right, v, val);
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
