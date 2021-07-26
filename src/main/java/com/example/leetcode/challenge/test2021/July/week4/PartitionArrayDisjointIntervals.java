package com.example.leetcode.challenge.test2021.July.week4;


import java.util.Arrays;

/**
 * Given an array nums, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * Example 2:
 *
 * Input: nums = [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 *
 * [32,57,24,19,0,24,49,67,87,87]
 *
 * [3,1,8,4,9,7,12,0,0,12,6,12,6,19,24,90,87,54,92,60,31,59,75,90,20,38,52,51,74,70,86,20,27,91,55,47,54,86,15,16,74,32,68,27,19,54,13,22,34,74,76,50,74,97,87,42,58,95,17,93,39,33,22,87,96,90,71,22,48,46,37,18,17,65,54,82,54,29,27,68,53,89,23,12,90,98,42,87,91,23,72,35,14,58,62,79,30,67,44,48]
 *
 */
public class PartitionArrayDisjointIntervals {
    public static void main(String[] args) {
        PartitionArrayDisjointIntervals partitionArrayDisjointIntervals = new PartitionArrayDisjointIntervals();
        partitionArrayDisjointIntervals.partitionDisjointV1(new int[]{32,57,24,19,0,24,49,67,87,87});
    }

    public int partitionDisjoint(int[] nums) {
        int length = nums.length;
        int curMax = Integer.MIN_VALUE;
        int[] backMin = new int[length];
        Arrays.fill(backMin, nums[length - 1]);
        for (int i = length - 2; i >= 0; --i) {
            backMin[i] = Math.min(backMin[i + 1], nums[i]);
        }
        for (int i = 0; i < length - 1; ++i) {
            curMax = Math.max(curMax, nums[i]);
            if (curMax <= backMin[i + 1])
                return i + 1;
        }
        return 0;
    }

    /**
     * faster solution
     * @param a
     * @return
     */
    public int partitionDisjointV1(int[] a) {
        int localMax = a[0], partitionIdx = 0, max = localMax;
        for (int i = 1; i < a.length; i++) {
            max = Math.max(max, a[i]);
            if (localMax > a[i]) {
                localMax = max;
                partitionIdx = i;
            }
        }
        return partitionIdx + 1;
    }
}
