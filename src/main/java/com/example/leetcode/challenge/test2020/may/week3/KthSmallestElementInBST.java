package com.example.leetcode.challenge.test2020.may.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * Output: 1
 * Example 2:
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class KthSmallestElementInBST {
    public static void main(String[] args) {
        KthSmallestElementInBST kthSmallestElementInBST = new KthSmallestElementInBST();
        char[] treeArr = new char[]{'5', '3', '6', '2', '4', '#', '#', '1'};
        int k = 3;
        TreeNode root = kthSmallestElementInBST.createTree(treeArr);
        int result = kthSmallestElementInBST.kthSmallest(root, k);
        System.out.println(result);
    }

    List<Integer> list = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        help(root);
        return list.get(k - 1);
    }

    void help(TreeNode root) {
        if (root == null)
            return;
        else {
            help(root.left);
            list.add(root.val);
            help(root.right);
        }
    }

    /**
     * faster solution
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallestV1(TreeNode root, int k) {
        int count = 0;
        int ksmall = Integer.MAX_VALUE;
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                count++;
                if (count == k) {
                    ksmall = current.val;
                    break;
                }
                current = current.right;
            } else {
                TreeNode prev = current.left;
                while (prev.right != null && prev.right != current)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = current;
                    current = current.left;
                } else {
                    prev.right = null;
                    count++;
                    if (count == k) {
                        ksmall = current.val;
                        break;
                    }
                    current = current.right;
                }
            }
        }

        return ksmall;

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
