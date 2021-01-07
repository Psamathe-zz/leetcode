package com.example.leetcode.challenge.December.week5;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 *
 *
 *
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 *
 * Input: root = [9]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The given binary tree will have between 1 and 10^5 nodes.
 * Node values are digits from 1 to 9.
 */
public class PseudoPalindromicPaths1 {
    public static void main(String[] args) {
        PseudoPalindromicPaths1 pseudoPalindromicPaths1 = new PseudoPalindromicPaths1();
        Integer[] array = new Integer[]{2,3,1,3,1,null,1};
        TreeNode root = pseudoPalindromicPaths1.convert(array);
        int res = pseudoPalindromicPaths1.pseudoPalindromicPaths(root);
        System.out.println(res);
    }

    int result = 0;
    Map<Integer,Integer> count = new HashMap<>();
    public int pseudoPalindromicPaths (TreeNode root) {
        helper(root);
        return result;
    }

    public void helper(TreeNode node){
        count.put(node.val,count.getOrDefault(node.val,0)+1);
        if(node.left != null)
            helper(node.left);
        if(node.right != null)
            helper(node.right);
        if(node.left == null && node.right == null){
            if(count())
                result++;
        }
        count.put(node.val,count.getOrDefault(node.val,0)-1);
    }

    public boolean count(){
        int res = 0;
        for (Map.Entry<Integer,Integer> entry : count.entrySet() ){
            if(entry.getValue() % 2 == 1)
                res++;
        }
        return res <= 1;
    }


    int countV1 = 0;

    public void preorderV1(TreeNode node, int path) {
        if (node != null) {
            // compute occurences of each digit
            // in the corresponding register
            path = path ^ (1 << node.val);
            // if it's a leaf check if the path is pseudo-palindromic
            if (node.left == null && node.right == null) {
                // check if at most one digit has an odd frequency
                if ((path & (path - 1)) == 0) {
                    ++countV1;
                }
            }
            preorderV1(node.left, path);
            preorderV1(node.right, path) ;
        }
    }

    public int pseudoPalindromicPathsV1 (TreeNode root) {
        preorderV1(root, 0);
        return countV1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

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
