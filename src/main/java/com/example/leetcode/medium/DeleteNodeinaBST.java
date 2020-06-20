package com.example.leetcode.medium;



public class DeleteNodeinaBST {
    public static void main(String[] args) {

    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null || root.right == null) {
                root = (root.left!=null) ? root.left : root.right;
            } else {
                TreeNode current = root.right;
                while (current.left != null)
                    current = current.left;
                root.val = current.val;
                root.right = deleteNode(root.right, current.val);
            }
        }
        return root;
    }
    TreeNode deleteNodeV2(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {
            if (root.right == null)
                return root.left;
            else {
                TreeNode cur = root.right;
                while (cur.left != null)
                    cur = cur.left;
                int temp = root.val;
                root.val = cur.val;
                cur.val = temp;
            }
        }
        root.left = deleteNodeV2(root.left, key);
        root.right = deleteNodeV2(root.right, key);
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
