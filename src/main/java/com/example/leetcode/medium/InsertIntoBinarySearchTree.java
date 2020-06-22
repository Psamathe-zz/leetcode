package com.example.leetcode.medium;

public class InsertIntoBinarySearchTree {
    public static void main(String[] args) {

    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode temp = root;
        TreeNode parent = root;
        while (temp!=null) {
            if (val < temp.val) {
                parent = temp;
                temp = temp.left;
            } else {
                parent = temp;
                temp = temp.right;
            }
        }
        TreeNode newNode = new TreeNode(val);
        if(val < parent.val)
            parent.left = newNode;
        else
            parent.right = newNode;
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


    public TreeNode createTree (char[] treeArr) {
        TreeNode[] tree = new TreeNode[treeArr.length];
        for (int i = 0; i < treeArr.length; i++) {
            if (treeArr[i] == '#') {
                tree[i] = null;
                continue;
            }
            tree[i] = new TreeNode(treeArr[i]-'0');
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
