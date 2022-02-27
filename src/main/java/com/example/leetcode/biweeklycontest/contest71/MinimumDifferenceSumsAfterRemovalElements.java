package com.example.leetcode.biweeklycontest.contest71;

import java.util.PriorityQueue;

/**
 * You are given a 0-indexed integer array nums consisting of 3 * n elements.
 *
 * You are allowed to remove any subsequence of elements of size exactly n from nums. The remaining 2 * n elements will be divided into two equal parts:
 *
 * The first n elements belonging to the first part and their sum is sumfirst.
 * The next n elements belonging to the second part and their sum is sumsecond.
 * The difference in sums of the two parts is denoted as sumfirst - sumsecond.
 *
 * For example, if sumfirst = 3 and sumsecond = 2, their difference is 1.
 * Similarly, if sumfirst = 2 and sumsecond = 3, their difference is -1.
 * Return the minimum difference possible between the sums of the two parts after the removal of n elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,2]
 * Output: -1
 * Explanation: Here, nums has 3 elements, so n = 1.
 * Thus we have to remove 1 element from nums and divide the array into two equal parts.
 * - If we remove nums[0] = 3, the array will be [1,2]. The difference in sums of the two parts will be 1 - 2 = -1.
 * - If we remove nums[1] = 1, the array will be [3,2]. The difference in sums of the two parts will be 3 - 2 = 1.
 * - If we remove nums[2] = 2, the array will be [3,1]. The difference in sums of the two parts will be 3 - 1 = 2.
 * The minimum difference between sums of the two parts is min(-1,1,2) = -1.
 * Example 2:
 *
 * Input: nums = [7,9,5,8,1,3]
 * Output: 1
 * Explanation: Here n = 2. So we must remove 2 elements and divide the remaining array into two parts containing two elements each.
 * If we remove nums[2] = 5 and nums[3] = 8, the resultant array will be [7,9,1,3]. The difference in sums will be (7+9) - (1+3) = 12.
 * To obtain the minimum difference, we should remove nums[1] = 9 and nums[4] = 1. The resultant array becomes [7,5,8,3]. The difference in sums of the two parts is (7+5) - (8+3) = 1.
 * It can be shown that it is not possible to obtain a difference smaller than 1.
 *
 */
public class MinimumDifferenceSumsAfterRemovalElements {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-difference-in-sums-after-removal-of-elements/solution/shi-ma-qing-kuang-liang-ge-you-xian-ji-d-657e/
     * @param nums
     * @return
     */
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        // min[i] 表示 nums[0] ~ nums[i] 个元素中选择 n 个元素和最小
        long[] min = new long[3 * n];

        // max[i] 表示 nums[i] ~ nums[3*n-1] 个元素中选择 n 个元素和最大
        long[] max = new long[3 * n];

        // 升序队列、 降序队列
        PriorityQueue<Integer> asc = new PriorityQueue<>();
        PriorityQueue<Integer> desc = new PriorityQueue<>((a, b) -> b - a);

        long sum_first = 0, sum_second = 0;
        for (int i = 0; i < 3 * n; i++) {
            int l = i, r = 3 * n - 1 - i;
            int left = nums[l], right = nums[r];
            sum_first += left;
            desc.add(left);

            sum_second += right;
            asc.add(right);

            if (i >= n) {
                sum_first -= desc.poll();
                sum_second -= asc.poll();
            }
            min[l] = sum_first;
            max[r] = sum_second;
        }

        // 遍历所有情况找到最小差值
        long minAns = Long.MAX_VALUE;
        for (int i = n - 1; i < 2 * n; i++) {
            minAns = Math.min(minAns, min[i] - max[i + 1]);
        }

        return minAns;
    }
}
