package com.example.leetcode.challenge.april.week5;


/**
 * Given a binary tree where each path going from the root to any leaf form a valid sequence, check if a given string is a valid sequence in such binary tree.
 *
 * We get the given string from the concatenation of an array of integers arr and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
 * Output: true
 * Explanation:
 * The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure).
 * Other valid sequences are:
 * 0 -> 1 -> 1 -> 0
 * 0 -> 0 -> 0
 * Example 2:
 *
 *
 *
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
 * Output: false
 * Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
 * Example 3:
 *
 *
 *
 * Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
 * Output: false
 * Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.
 */
public class CheckStringIsValidSequence {
    public static void main(String[] args) {
        char[] nodes = new char[]{'0','1','0','0','1','0','#','#','1','0','0'};
        int[] arr = new int[]{0,1,0,1};
        CheckStringIsValidSequence checkStringIsValidSequence = new CheckStringIsValidSequence();
        TreeNode root = checkStringIsValidSequence.createTree(nodes);
        boolean result = checkStringIsValidSequence.isValidSequence(root,arr);
        System.out.println(result);
    }

    public boolean isValidSequence(TreeNode root, int[] arr) {

        return dfs(root,arr,0);
    }

    public boolean dfs(TreeNode node,int[] arr,int start){
         if( start>= arr.length || node.val != arr[start]){
            return false;
        }

        if(node.left == null && node.right == null){
            if(start == arr.length - 1 && node.val == arr[start])
                return true;
            else
                return false;
        }
        else if(node.left == null)
            return dfs(node.right,arr,start+1);
        else if(node.right == null)
            return dfs(node.left,arr,start+1);
        else
            return dfs(node.left,arr,start+1) || dfs(node.right,arr,start+1);
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
}
