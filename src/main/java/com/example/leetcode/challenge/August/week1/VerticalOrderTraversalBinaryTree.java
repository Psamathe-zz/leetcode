package com.example.leetcode.challenge.August.week1;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 *
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 *
 * Note:
 *
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 * [0,5,1,9,null,2,null,null,null,null,3,4,8,6,null,null,null,7]
 */
public class VerticalOrderTraversalBinaryTree {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{0,5,1,9,null,2,null,null,null,null,3,4,8,6,null,null,null,7};
        VerticalOrderTraversalBinaryTree verticalOrderTraversalBinaryTree = new VerticalOrderTraversalBinaryTree();
        TreeNode root = verticalOrderTraversalBinaryTree.convert(arr);
        verticalOrderTraversalBinaryTree.verticalTraversal(root);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        List<List<Integer>> allList = new ArrayList<>();
        for(Map.Entry<Integer, TreeMap<Integer, List<Integer>>> entry: map.entrySet()) {
            TreeMap<Integer, List<Integer>> ymap = entry.getValue();
            List<Integer> list = new ArrayList<>();
            for(Map.Entry<Integer, List<Integer>> yentry: ymap.entrySet()) { // here it's order with y from small to big
                List<Integer> items = yentry.getValue();
                Collections.sort(items);
                list.addAll(0, items);
            }
            allList.add(list);
        }
        return allList;
    }

    // map is x--> y-->list<val>
    void dfs(TreeNode node, int x, int y, TreeMap<Integer, TreeMap<Integer, List<Integer>>> map) {
        if(null == node) return;
        TreeMap<Integer, List<Integer>> ymap = map.get(x);
        if(null == ymap) {
            ymap = new TreeMap<>();
            map.put(x, ymap);
        }
        List<Integer> list = ymap.get(y);
        if(null == list) {
            list = new ArrayList<>();
            ymap.put(y, list);
        }
        list.add(node.val);
        dfs(node.left, x-1, y-1, map);
        dfs(node.right, x+1, y-1, map);
    }


    public static class TreeNode {
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
