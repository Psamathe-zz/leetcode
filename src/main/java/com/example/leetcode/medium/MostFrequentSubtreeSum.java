package com.example.leetcode.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum.
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
 * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 *
 * Examples 1
 * Input:
 *
 *   5
 *  /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * Examples 2
 * Input:
 *
 *   5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 */
public class MostFrequentSubtreeSum {

    public static void main(String[] args) {
        char[] nodes = new char[]{'2', '3', '4'};
        MostFrequentSubtreeSum mostFrequentSubtreeSum = new MostFrequentSubtreeSum();
        TreeNode root = mostFrequentSubtreeSum.createTree(nodes);
        int[] result = mostFrequentSubtreeSum.findFrequentTreeSum(root);
        System.out.println(result);
    }

    Map<Integer,Integer> myMap = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        help(root);
        int max = myMap.values().stream().sorted(Comparator.reverseOrder()).findFirst().orElse(0);
        return myMap.entrySet().stream().filter(e->e.getValue()==max).map(e->e.getKey()).mapToInt(e -> e).toArray();
    }

    public int help(TreeNode node){
        if(node == null)
            return 0;
        int result = node.val + help(node.left) + help(node.right);
        myMap.put(result,myMap.getOrDefault(result,0) + 1);
        return result;
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
