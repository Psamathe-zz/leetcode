package com.example.leetcode.challenge.september.week2;

/**
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 *
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 *
 * Return the sum of these numbers.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 *
 * Note:
 *
 * The number of nodes in the tree is between 1 and 1000.
 * node.val is 0 or 1.
 * The answer will not exceed 2^31 - 1.
 */
public class SumRootToLeafBinaryNumbers {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,0,1,0,1,0,1};
        SumRootToLeafBinaryNumbers sumRootToLeafBinaryNumbers = new SumRootToLeafBinaryNumbers();
        TreeNode root = sumRootToLeafBinaryNumbers.convert(array);
        sumRootToLeafBinaryNumbers.sumRootToLeaf(root);
    }

    int res = 0;
    public int sumRootToLeaf(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        helper(root,stringBuilder);
        return res;
    }

    public void helper(TreeNode node,StringBuilder stringBuilder){
            stringBuilder.append(node.val);
            if(node.left == null && node.right == null){
                int temp = Integer.parseInt(stringBuilder.toString(), 2);
                res += temp;
            }
            if(node.left != null) {
                helper(node.left, stringBuilder);
            }
            if(node.right != null) {
                helper(node.right, stringBuilder);
            }
            int length = stringBuilder.length();
            stringBuilder.setLength(length - 1);

    }


    /**
     * faster solution
     * @param root
     * @return
     */
    public int sumRootToLeafV1(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int val){
        if(root == null) return 0;
        val = val * 2 + root.val;
        if(root.left == null && root.right == null){
            return val;
        } else {
            return dfs(root.left, val) + dfs(root.right, val);
        }
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
