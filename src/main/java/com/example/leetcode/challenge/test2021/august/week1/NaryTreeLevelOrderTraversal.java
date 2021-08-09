package com.example.leetcode.challenge.test2021.august.week1;


import com.example.leetcode.model.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 * Example 2:
 *
 *
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 */
public class NaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if(root != null)
            queue.add(root);
        int size;
        List<Integer> list;
        while (!queue.isEmpty()){
            list = new ArrayList<>();
            size = queue.size();
            while (size > 0) {
                Node node = queue.poll();
                list.add(node.val);
                for (Node n : node.children){
                    if(n != null)
                        queue.add(n);
                }
                size--;
            }
            res.add(list);
        }

        return res;
    }
}
