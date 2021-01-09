package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with same node values.
 *
 * Example 1:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * The following are two duplicate subtrees:
 *
 *       2
 *      /
 *     4
 * and
 *
 *     4
 * Therefore, you need to return above trees' root in the form of a list.
 * [1,2,3,4,null,2,4,null,null,4]
 */
public class FindDuplicateSubtrees {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,null,2,4,null,null,4};
        FindDuplicateSubtrees findDuplicateSubtrees = new FindDuplicateSubtrees();
        TreeNode node = findDuplicateSubtrees.convert(arr);
        findDuplicateSubtrees.findDuplicateSubtrees(node);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/7500082.html
     * 后序遍历，还有数组序列化，并且建立序列化跟其出现次数的映射，
     * 这样如果我们得到某个结点的序列化字符串，而该字符串正好出现的次数为1，说明之前已经有一个重复树了，
     * 我们将当前结点存入结果res，这样保证了多个重复树只会存入一个结点
     */
    Map<String,Integer> map;
    List<TreeNode> res;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new ArrayList<>();
        map = new HashMap<>();
        helper(root);
        return res;
    }


    String helper(TreeNode node) {
        if (node == null)
            return "#";
        String str = node.val + "," + helper(node.left) + "," + helper(node.right);
        if (map.containsKey(str) && map.get(str) == 1) {
            System.out.println(str);
            System.out.println(map.get(str));
            res.add(node);
        }
        map.put(str,map.getOrDefault(str,0) + 1);
        return str;
    }


    /**
     * faster solution
     */
    Map<Long, TreeNode> duplicateTreesMap;
    Map<Long, TreeNode> treesToHashValue;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtreesV2(TreeNode root) {
        duplicateTreesMap = new HashMap<Long, TreeNode>();
        treesToHashValue = new HashMap<Long, TreeNode>();
        ans = new ArrayList<TreeNode>();
        lookup(root);

        for(Map.Entry<Long, TreeNode> entry : duplicateTreesMap.entrySet()){
            ans.add(entry.getValue());
        }

        return ans;
    }

    public long lookup(TreeNode root) {
        if (root == null)
            return 31;
        long left = lookup(root.left);
        long right = lookup(root.right);
        long val = root.val + 5381;
        long hash = val + val * left + val * left * right;
        if(treesToHashValue.get(hash) == null) {
            treesToHashValue.put(hash, root);
        } else if(duplicateTreesMap.get(hash) == null) {
            duplicateTreesMap.put(hash, root);
        }
        return hash;
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
