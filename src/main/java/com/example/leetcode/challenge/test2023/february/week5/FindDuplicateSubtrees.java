package com.example.leetcode.challenge.test2023.february.week5;

import com.example.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 * Example 2:
 *
 *
 * Input: root = [2,1,1]
 * Output: [[1]]
 * Example 3:
 *
 *
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {

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
}
