package com.example.leetcode.challenge.test2020.October.week2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The input tree is guaranteed to be a binary search tree.
 */
public class SerializeAndDeserializeBST {
    public static void main(String[] args) {

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        preOrder(root, sb);
        return sb.toString();
    }

    byte[] intToByte(int val) {
        return new byte[]{
                (byte)(val >> 24),
                (byte)(val >> 16),
                (byte)(val >> 8),
                (byte)val
        };
    }

    void preOrder(TreeNode root, StringBuffer sb) {
        if (root != null) {
            byte[] tmp = intToByte(root.val);
            sb.append((char) tmp[0]).append((char) tmp[1]).append((char) tmp[2]).append((char) tmp[3]);
            preOrder(root.left, sb);
            preOrder(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return helper(data.toCharArray(), new int[]{0}, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode helper(char[] data, int[] pos, int low, int high) {
        if(pos[0] >= data.length) return null;
        int val =
                data[pos[0] + 0] << 24 & (0xff000000) |
                        data[pos[0] + 1] << 16 & (0x00ff0000) |
                        data[pos[0] + 2] << 8 & (0x0000ff00) |
                        data[pos[0] + 3] << 0 & (0x000000ff);
        if(val < low || val > high) return null;
        TreeNode root = new TreeNode(val);
        pos[0] += 4;
        root.left = helper(data, pos, low, val);
        root.right = helper(data, pos, val, high);
        return root;
    }

    int byteToInt(byte[] bytes) {
        return
                bytes[0] & (0xff000000) |
                        bytes[1] & (0x00ff0000) |
                        bytes[2] & (0x0000ff00) |
                        bytes[3] & (0x000000ff);
    }

    /**
     * V2
     */

    // Encodes a tree to a single string.
    public String serializeV2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        recurS(root, sb);
        return sb.toString();
    }

    private void recurS(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val);
        sb.append(',');
        recurS(node.left, sb);
        recurS(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeV2(String data) {
        if (data.length() <= 0) {
            return null;
        }
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return recurD(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode recurD(Queue<String> queue, int min, int max) {
        if (queue.size() <= 0) {
            return null;
        }
        int val = Integer.parseInt(queue.peek());
        if (val <=  min || val >= max) {
            return null;
        }
        queue.poll();
        TreeNode node = new TreeNode(val);
        node.left = recurD(queue, min, val);
        node.right = recurD(queue, val, max);
        return node;
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
