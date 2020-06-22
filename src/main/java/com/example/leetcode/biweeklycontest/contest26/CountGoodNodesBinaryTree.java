package com.example.leetcode.biweeklycontest.contest26;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * Example 2:
 *
 *
 *
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 *
 * Constraints:
 *
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 */
public class CountGoodNodesBinaryTree {

    public static void main(String[] args) {
        char[] treeArr = new char[]{'3','1','4','3','#','1','5'};
        CountGoodNodesBinaryTree countGoodNodesBinaryTree = new CountGoodNodesBinaryTree();
        TreeNode root = countGoodNodesBinaryTree.createTree(treeArr);
        int result = countGoodNodesBinaryTree.goodNodes(root);
        System.out.println(result);
    }

    int temp = 0;
    public int goodNodes(TreeNode root) {
        if(root == null)
            return 0;
        help(root,Integer.MIN_VALUE);
        return temp;
    }

    public void help(TreeNode root,int value){
        if(root.val >= value)
            temp++;
        if(root.left != null)
            help(root.left,Math.max(root.val,value));
        if(root.right != null)
            help(root.right,Math.max(root.val,value));
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

    public TreeNode createTree(char[] treeArr) {
        TreeNode[] tree = new TreeNode[treeArr.length];
        for (int i = 0; i < treeArr.length; i++) {
            if (treeArr[i] == '#') {
                tree[i] = null;
                continue;
            }
            tree[i] = new TreeNode(treeArr[i] - '0');
        }
        int pos = 0;
        for (int i = 0; i < treeArr.length && pos < treeArr.length - 1; i++) {
            if (tree[i] != null) {
                tree[i].left = tree[++pos];
                if (pos < treeArr.length - 1) {
                    tree[i].right = tree[++pos];
                }
            }
        }
        return tree[0];
    }
}
