package com.example.leetcode.medium;

/**
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 *
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [0,1,2,3,4,3,4]
 * Output: "dba"
 * Example 2:
 *
 *
 *
 * Input: [25,1,3,1,3,0,2]
 * Output: "adz"
 * Example 3:
 *
 *
 *
 * Input: [2,2,1,null,1,0,null,0]
 * Output: "abc"
 *
 *
 * Note:
 *
 * The number of nodes in the given tree will be between 1 and 8500.
 * Each node in the tree will have a value between 0 and 25.
 * [2,2,1,null,1,0,null,0]
 */
public class SmallestStringStartingFromLeaf {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{0,1,2,3,4,3,4};
        SmallestStringStartingFromLeaf smallestStringStartingFromLeaf = new SmallestStringStartingFromLeaf();
        TreeNode root = smallestStringStartingFromLeaf.convert(arr);
        smallestStringStartingFromLeaf.smallestFromLeafV2(root);
    }

    String result;
    public String smallestFromLeaf(TreeNode root) {
        result = "";
        helper(root,"");
        return result;
    }

    public void helper(TreeNode node,String pre){
        if(node == null)
            return;
        String temp = (char)('a' + node.val ) + pre;
        if(node.left == null && node.right == null) {
            if(result.isEmpty() || temp.compareTo(result) < 0)
                result = temp;
            return;
        }
        helper(node.left,temp);
        helper(node.right,temp);
    }


    /**
     * faster solution
     */
    String ans = "";
    public String smallestFromLeafV2(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    public void dfs(TreeNode node, StringBuilder sb) {
        if (node == null)
            return;
        sb.append((char)('a' + node.val));

        if (node.left == null && node.right == null) {
            sb.reverse();
            String S = sb.toString();
            sb.reverse();

            if ( ans.isEmpty() || S.compareTo(ans) < 0)
                ans = S;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
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
