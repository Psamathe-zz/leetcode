package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 *
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 *
 *
 *
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 *
 *
 *
 * Example 1:
 *
 * Input: label = 14
 * Output: [1,3,4,14]
 * Example 2:
 *
 * Input: label = 26
 * Output: [1,2,6,10,26]
 *
 */
public class PathInZigzagLabelledBinaryTree {
    public static void main(String[] args) {
        PathInZigzagLabelledBinaryTree pathInZigzagLabelledBinaryTree = new PathInZigzagLabelledBinaryTree();
        List<Integer> res = pathInZigzagLabelledBinaryTree.pathInZigZagTree(26);
        System.out.println(res);
    }

    public List<Integer> pathInZigZagTree(int label) {
        if(label == 1){
            List<Integer> list =  new ArrayList<>();
            list.add(1);
            return list;
        }

        int parent =findParent(label);
        List<Integer> parentList = pathInZigZagTree(parent);
        parentList.add(label);
        return parentList;
    }

    public int findParent(int label){
        int max = 1;
        while (max <= label){
            max *= 2;
            if(max * 2 > label)
                break;
        }
        int parent = (max * 2 - label - 1) / 2 + max / 2;
        return parent;
    }
}
