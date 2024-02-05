package com.example.leetcode.weeklycontest.old.test190;

/**
 * Given a binary tree where node values are digits from 1 to 9.
 * A path in the binary tree is said to be pseudo-palindromic
 * if at least one permutation of the node values in the path is a palindrome.
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
 * Explanation: The figure above represents the given binary tree.
 * There are three paths going from the root node to leaf nodes: the red path [2,3,3],the green path [2,1,1], and the path [2,3,1].
 * Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and
 * the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 *
 *
 *
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree.
 * There are three paths going from the root node to leaf nodes: the green path [2,1,1],the path [2,1,3,1], and the path [2,1].
 * Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
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
public class PseudoPalindromicPathsInBinaryTree {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{2,3,1,3,1,null,1};
        PseudoPalindromicPathsInBinaryTree pseudoPalindromicPathsInBinaryTree = new PseudoPalindromicPathsInBinaryTree();
        TreeNode root = pseudoPalindromicPathsInBinaryTree.convert(array);
        int result = pseudoPalindromicPathsInBinaryTree.pseudoPalindromicPaths(root);
        System.out.println(result);
    }
    public int pseudoPalindromicPaths (TreeNode root) {
        if(root==null) return 0;
        help(root,0);
        return ans;
    }
    int ans = 0;
    public void help(TreeNode root, int s){
        if(root == null)
            return;
        s ^= 1 << root.val;
        if( root.left == null && root.right == null){
            if(s==0||(s&(s-1))==0){
                ans++;
            }
        }
        if(root.left!=null) help(root.left,s);
        if(root.right!=null) help(root.right,s);
        return;

    }

    /**
     * faster solution
     * @param root
     * @return
     */
    public int pseudoPalindromicPathsV2(TreeNode root) {
        return helper(root, new int[10]);
    }

    int helper(TreeNode root, int[] counts) {
        if (root == null) return 0;
        counts[root.val]++;
        if (root.left == null && root.right == null) {
            int count = palindromic(counts) ? 1 : 0;
            counts[root.val]--;
            return count;
        }
        int count = helper(root.left, counts) + helper(root.right, counts);
        counts[root.val]--;
        return count;
    }

    boolean palindromic(int[] counts) {
        int countOdd = 0;
        for (int f : counts) {
            if (f % 2 == 1) {
                if (countOdd == 0) countOdd++;
                else return false;
            }
        }
        return true;
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
