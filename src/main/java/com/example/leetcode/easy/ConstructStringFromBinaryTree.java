package com.example.leetcode.easy;

/**
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 *
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 *
 * Example 1:
 * Input: Binary tree: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 *
 * Output: "1(2(4))(3)"
 *
 * Explanation: Originallay it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs.
 * And it will be "1(2(4))(3)".
 * Example 2:
 * Input: Binary tree: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *
 * Output: "1(2()(4))(3)"
 *
 * Explanation: Almost the same as the first example,
 * except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 */
public class ConstructStringFromBinaryTree {

    public static void main(String[] args) {

    }

    public String tree2str(TreeNode t) {
        StringBuilder stringBuilder = new StringBuilder();
        if(t==null)
            return stringBuilder.toString();
        stringBuilder.append(t.val);
        if(t.left == null){
            if(t.right != null)
                stringBuilder.append("()");
        } else {
            stringBuilder.append("("+tree2str(t.left)+")");
        }
        if(t.right != null){
            stringBuilder.append("("+tree2str(t.right)+")");
        }
        return stringBuilder.toString();
    }


    /**
     * faster solution
     * @param t
     * @return
     */
    public String tree2strV1(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        preorder(t, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null)
            return;

        sb.append(node.val);

        if (node.left != null || node.right != null) {
            sb.append("(");
        }
        preorder(node.left, sb);
        if (node.left != null || node.right != null) {
            sb.append(")");
        }

        if (node.right != null) {
            sb.append("(");
        }
        preorder(node.right, sb);
        if (node.right != null) {
            sb.append(")");
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
