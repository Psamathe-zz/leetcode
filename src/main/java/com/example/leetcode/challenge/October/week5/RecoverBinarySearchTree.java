package com.example.leetcode.challenge.October.week5;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 *
 *
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 */
public class RecoverBinarySearchTree {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/4298069.html
     * @param root
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();

        inorder(root, list, vals);
        Collections.sort(vals);
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).val = vals.get(i);
        }
    }

    void inorder(TreeNode root, List<TreeNode> list, List<Integer> vals) {
        if (root == null)
            return;
        inorder(root.left, list, vals);
        list.add(root);
        vals.add(root.val);
        inorder(root.right, list, vals);
    }


    /**
     * faster solution
     * @param root
     */
    public void recoverTreeV1(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left != null){
                TreeNode p = cur.left;
                while (p.right != null && p.right != cur)
                    p = p.right;
                if (null == p.right) {
                    p.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    p.right = null;
                }
            }
            if (null != pre && cur.val < pre.val){
                if (null == first)
                    first = pre;
                second = cur;
            }
            pre = cur;
            cur = cur.right;
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
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
