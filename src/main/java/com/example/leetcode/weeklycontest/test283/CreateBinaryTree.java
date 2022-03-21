package com.example.leetcode.weeklycontest.test283;

import com.example.leetcode.model.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,
 *
 * If isLefti == 1, then childi is the left child of parenti.
 * If isLefti == 0, then childi is the right child of parenti.
 * Construct the binary tree described by descriptions and return its root.
 *
 * The test cases will be generated such that the binary tree is valid.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * Output: [50,20,80,15,17,19]
 * Explanation: The root node is the node with value 50 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 * Example 2:
 *
 *
 * Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * Output: [1,2,null,null,3,4]
 * Explanation: The root node is the node with value 1 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 *
 */
public class CreateBinaryTree {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode-cn.com/problems/create-binary-tree-from-descriptions/solution/shi-ma-qing-kuang-gou-zao-shu-zhao-gen-j-b0wi/
     * @param descriptions
     * @return
     */
    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<Integer> chlids = new HashSet<>();

        Map<Integer, TreeNode> map = new HashMap<>();
        // 构造树
        for (int[] d : descriptions) {
            int pVal = d[0], cVal = d[1];
            boolean left = (d[2] == 1) ? true : false;

            TreeNode pNode = map.get(pVal);
            if (pNode == null) {
                pNode = new TreeNode(pVal);
                map.put(pVal, pNode);
            }

            TreeNode cNode = map.get(cVal);
            if (cNode == null) {
                cNode = new TreeNode(cVal);
                map.put(cVal, cNode);
            }
            chlids.add(cVal);

            if (left) {
                pNode.left = cNode;
            } else {
                pNode.right = cNode;
            }
        }

        // 找到根节点
        TreeNode root = null;
        for (int v : map.keySet()) {
            if (chlids.contains(v)) continue;
            else {
                root = map.get(v);
                break;
            }
        }

        return root;
    }
}
