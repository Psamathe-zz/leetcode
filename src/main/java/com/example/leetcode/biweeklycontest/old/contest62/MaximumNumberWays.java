package com.example.leetcode.biweeklycontest.old.contest62;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed integer array nums of length n. The number of ways to partition nums is the number of pivot indices that satisfy both conditions:
 *
 * 1 <= pivot < n
 * nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]
 * You are also given an integer k. You can choose to change the value of one element of nums to k, or to leave the array unchanged.
 *
 * Return the maximum possible number of ways to partition nums to satisfy both conditions after changing at most one element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,-1,2], k = 3
 * Output: 1
 * Explanation: One optimal approach is to change nums[0] to k. The array becomes [3,-1,2].
 * There is one way to partition the array:
 * - For pivot = 2, we have the partition [3,-1 | 2]: 3 + -1 == 2.
 * Example 2:
 *
 * Input: nums = [0,0,0], k = 1
 * Output: 2
 * Explanation: The optimal approach is to leave the array unchanged.
 * There are two ways to partition the array:
 * - For pivot = 1, we have the partition [0 | 0,0]: 0 == 0 + 0.
 * - For pivot = 2, we have the partition [0,0 | 0]: 0 + 0 == 0.
 * Example 3:
 *
 * Input: nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
 * Output: 4
 * Explanation: One optimal approach is to change nums[2] to k. The array becomes [22,4,-33,-20,-15,15,-16,7,19,-10,0,-13,-14].
 * There are four ways to partition the array.
 */
public class MaximumNumberWays {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-ways-to-partition-an-array/solution/java-qian-zhui-he-shuang-ha-xi-biao-mei-hq1ur/
     * @param nums
     * @param k
     * @return
     */
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
            // 一开始除了最后一个前缀和，其余均在m2中
            if (i != n) m2.put(preSum[i], m2.getOrDefault(preSum[i], 0) + 1);
        }
        int ans = 0;
        if (preSum[n] % 2 == 0) {
            // 若一个元素都不修改
            ans = m2.getOrDefault(preSum[n] / 2, 0);
        }
        for (int i = 0; i < n; i++) {
            // 修改下标为i的元素变成k
            int change = k - nums[i];
            // 所有元素总和的变化量也是change
            int pn = preSum[n] + change;
            if (pn % 2 == 0) {
                // 在m1中我们要找的目标是 target1 = 总和/2 的数量
                int t1 = pn / 2;
                // 在m2中我们要找的目标是 target2 = target1 - change 的数量
                int t2 = t1 - change;
                int found = m1.getOrDefault(t1, 0) + m2.getOrDefault(t2, 0);
                ans = Math.max(ans, found);
            }
            // 不断减少m2中的计数，增加m1对应的计数
            if (i != n - 1) {
                m1.put(preSum[i + 1], m1.getOrDefault(preSum[i + 1], 0) + 1);
                int tmp = m2.get(preSum[i + 1]);
                if (tmp == 1) {
                    m2.remove(preSum[i + 1]);
                } else {
                    m2.put(preSum[i + 1], tmp - 1);
                }
            }
        }
        return ans;

    }
}
