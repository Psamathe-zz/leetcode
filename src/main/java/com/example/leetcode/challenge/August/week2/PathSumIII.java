package com.example.leetcode.challenge.August.week2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class PathSumIII {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1};
        int sum = 1;
        PathSumIII pathSumIII = new PathSumIII();
        TreeNode root = pathSumIII.convert(arr);
        int res = pathSumIII.pathSum(root,sum);
        System.out.println(res);
    }

    int res;
    public int pathSum(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();
        res = 0;
        helper(list,root,sum);
        return res;
    }

    public void helper(List<Integer> list , TreeNode node,int sum){
        if(node == null)
            return;
        for (Integer val : list){
            if(val + node.val == sum)
                res++;
        }
        list = list.stream().map(e->e + node.val).collect(Collectors.toList());
        if(node.val == sum)
            res++;
        list.add(node.val);
        helper(list,node.left,sum);
        helper(list,node.right,sum);
    }


    /**
     * faster solution
     * @param root
     * @param sum
     * @return
     */
    public int pathSumV1(TreeNode root, int sum) {
        // prefixSum, numOccurrence
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        return dfs(root, sum, map, 0);
    }

    private int dfs(TreeNode curr, int target, Map<Integer, Integer> map, int sumSoFar) {
        if (curr == null)
            return 0;

        sumSoFar += curr.val;

        int res = map.getOrDefault(sumSoFar - target, 0);
        map.put(sumSoFar, map.getOrDefault(sumSoFar, 0) + 1);

        res += dfs(curr.left, target, map, sumSoFar) + dfs(curr.right, target, map, sumSoFar);
        map.put(sumSoFar, map.get(sumSoFar) - 1);

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
