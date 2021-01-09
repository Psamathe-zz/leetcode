package com.example.leetcode.challenge.test2020.april.week5;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        char[] nodes = new char[]{'1','2','3'};

        BinaryTreeMaximumPathSum binaryTreeMaximumPathSum = new BinaryTreeMaximumPathSum();
        TreeNode root = binaryTreeMaximumPathSum.createTree(nodes);
        int result = binaryTreeMaximumPathSum.maxPathSum(root);
        System.out.println(result);
    }

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
         help(root);
         return max;
    }

    public int help(TreeNode node){
        if(node == null)
            return 0;
        int leftMax = help(node.left);
        int rightMax = help(node.right);

        int triangleSum = leftMax + node.val + rightMax;
        int leftSum = leftMax + node.val;
        int rightSum = rightMax + node.val;

        int maxAtNode = Math.max(node.val, Math.max(leftSum, rightSum));
        max = Math.max(max, Math.max(triangleSum, maxAtNode));

        return maxAtNode;
    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public int maxPathSumV2(TreeNode root) {
        helperV2(root);
        return max;
    }

    public int helperV2(TreeNode root) {
        if(root == null) return 0;

        int left = Math.max(0, helperV2(root.left));
        int right = Math.max(0, helperV2(root.right));

        max = Math.max(max, left+right+root.val);

        return Math.max(left, right) + root.val;
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


    public TreeNode createTree (char[] treeArr) {
        TreeNode[] tree = new TreeNode[treeArr.length];
        for (int i = 0; i < treeArr.length; i++) {
            if (treeArr[i] == '#') {
                tree[i] = null;
                continue;
            }
            tree[i] = new TreeNode(treeArr[i]-'0');
        }
        int pos = 0;
        for (int i = 0; i < treeArr.length && pos < treeArr.length-1; i++) {
            if (tree[i] != null) {
                tree[i].left = tree[++pos];
                if (pos < treeArr.length-1) {
                    tree[i].right = tree[++pos];
                }
            }
        }
        return tree[0];
    }
}
