package com.example.leetcode.challenge.test2020.July.week1;


import java.util.*;


/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {

    }
    TreeMap<Integer,List<Integer>> map;
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        map  = new TreeMap<>(Collections.reverseOrder());

        helper(root,0);

        List<List<Integer>> result = new ArrayList<>();
        return result;
    }

    public void helper(TreeNode node,int level){
        if(node == null)
            return;
        List<Integer> list = map.getOrDefault(level,new ArrayList<>());
        list.add(node.val);
        map.put(level,list);
        helper(node.left,level+1);
        helper(node.right,level+1);
    }

    /**
     * faster solution
     */

    public List<List<Integer>> levelOrderBottomV1(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if(root == null) {
            return resList;
        }

        dfs(root, 0, resList);
        return resList;
    }

    public void dfs(TreeNode root, int depth, List<List<Integer>> res) {
        if(root == null) {
            return;
        }

        if(depth == res.size()) {
            res.add(0, new ArrayList<Integer>());
        }

        res.get(res.size() - depth - 1).add(root.val);
        dfs(root.left, depth + 1, res);
        dfs(root.right, depth + 1, res);
    }


    /**
     * less memory
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottomV2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
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
