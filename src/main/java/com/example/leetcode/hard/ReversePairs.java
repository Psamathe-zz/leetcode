package com.example.leetcode.hard;

import java.util.Arrays;

/**
 *
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 *
 * You need to return the number of important reverse pairs in the given array.
 *
 * Example1:
 *
 * Input: [1,3,2,3,1]
 * Output: 2
 * Example2:
 *
 * Input: [2,4,3,5,1]
 * Output: 3
 * Note:
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 *
 * https://www.cnblogs.com/grandyang/p/6657956.html
 *http://www.noteanddata.com/leetcode-493-Reverse-Pairs-java-solution-note.html#%E8%B6%85%E6%97%B6%E7%9A%84binary-search-tree%E7%9A%84%E5%81%9A%E6%B3%95
 * [2147483647,2147483647,2147483647,2147483647,2147483647,2147483647]
 */
public class ReversePairs {
    public static void main(String[] args) {
        int[] nums = new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        ReversePairs reversePairs = new ReversePairs();
        reversePairs.reversePairs(nums);
    }

    public int reversePairs(int[] nums) {
        return mergeCount(nums, 0, nums.length-1);
    }
    int mergeCount(int[] nums, int from, int to) {
        if(from >= to)
            return 0;
        int mid = from + (to-from)/2;
        int count = mergeCount(nums, from, mid) + mergeCount(nums, mid+1, to);
        for(int i = from, j = mid+1; i <= mid; i++) {
            while(j <= to && nums[i]/2.0 >   nums[j]) {
                j++;
            }
            count += j-mid-1;
        }
        // here we are not doing merge-sort here???
        Arrays.sort(nums, from, to+1);
        return count;
    }


    /**
     * 二叉樹
     */
    static class Node {
        Node left, right;
        int val, leftCount, dupCount;
        public Node(int val) {
            this.val = val;
            this.leftCount = 0;
            this.dupCount = 1;
        }
    }
    public int reversePairsV2(int[] nums) {
        if(null == nums || nums.length <=1 ) return 0;
        int count = 0;
        Node root = new Node(nums[nums.length-1]);
        for(int i = nums.length-2; i >= 0; --i) {
            count += search(root, nums[i]/2.0);
            insert(root, nums[i]);
        }
        return count;
    }
    int search(Node node, double val) {
        if(null == node) return 0;

        if((double)node.val == val) {
            return node.leftCount;
        }
        else if(val > (double)node.val ) {
            return node.leftCount + node.dupCount + search(node.right, val);
        }
        else { // val < node.val
            return search(node.left, val);
        }

    }

    void insert(Node node, int val) {
        if(node.val == val) {
            node.dupCount++;
        }
        else if(val > node.val) {
            if(node.right == null) {
                node.right = new Node(val);
            }
            else {
                insert(node.right, val);
            }
        }
        else { // val < node.val
            node.leftCount++;
            if(node.left == null) {
                node.left = new Node(val);
            }
            else {
                insert(node.left, val);
            }
        }
    }
}
