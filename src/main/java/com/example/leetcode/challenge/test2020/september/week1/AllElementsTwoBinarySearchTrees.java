package com.example.leetcode.challenge.test2020.september.week1;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two binary search trees root1 and root2.
 *
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 *
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 * Example 3:
 *
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 * Example 4:
 *
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 * Example 5:
 *
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 *
 * Constraints:
 *
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 */
public class AllElementsTwoBinarySearchTrees {
    public static void main(String[] args) {

    }

    List<Integer> res = new LinkedList<>();
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        TraverseTree(root1);
        TraverseTree(root2);
        Collections.sort(res);
        return res;
    }

    public void TraverseTree(TreeNode node){
        if(node != null) {
            res.add(node.val);
            TraverseTree(node.left);
            TraverseTree(node.right);
        }
    }


    /**
     * faster solution
     * @param root1
     * @param root2
     * @return
     */
    // do an inorder on the first BST. It will create an sorted array
    // Now, compare element by element with this array and the second BST
    // inorder fashion. Add the elements one by one - finish the second BST
    // The first list (created from the BST) may or may not be finished.
    // Now, after we complete the merge subroutine,
    // first list still may have few entries. Add those in the result
    public List<Integer> getAllElementsV1(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> tree = new LinkedList<>();
        LinkedList<Integer> merged = new LinkedList<>();

        inorder(root1, tree);
        merge(root2, merged, tree);

        merged.addAll(tree);

        return merged;
    }

    private void inorder(TreeNode node, LinkedList<Integer> list) {
        if (node == null) return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    private void merge(TreeNode n1, LinkedList<Integer> list, LinkedList<Integer> list1) {
        if (n1 == null) return;

        merge(n1.left, list, list1);

        while (!list1.isEmpty() && list1.peek() < n1.val) {
            list.add(list1.poll());
        }

        list.add(n1.val);

        merge(n1.right, list, list1);
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
