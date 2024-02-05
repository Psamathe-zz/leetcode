package com.example.leetcode.weeklycontest.old.test325;

/**
 * You are given an array nums consisting of positive integers and an integer k.
 *
 * Partition the array into two ordered groups such that each element is in exactly one group. A partition is called great if the sum of elements of each group is greater than or equal to k.
 *
 * Return the number of distinct great partitions. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Two partitions are considered distinct if some element nums[i] is in different groups in the two partitions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], k = 4
 * Output: 6
 * Explanation: The great partitions are: ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) and ([4], [1,2,3]).
 * Example 2:
 *
 * Input: nums = [3,3,3], k = 4
 * Output: 0
 * Explanation: There are no great partitions for this array.
 * Example 3:
 *
 * Input: nums = [6,6], k = 2
 * Output: 2
 * Explanation: We can either put nums[0] in the first partition or in the second partition.
 * The great partitions will be ([6], [6]) and ([6], [6]).
 */
public class NumberGreatPartitions {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/number-of-great-partitions/solution/ni-xiang-si-wei-01-bei-bao-fang-an-shu-p-v47x/
     * @param nums
     * @param k
     * @return
     */
    private static final int MOD = (int) 1e9 + 7;

    public int countPartitions(int[] nums, int k) {
        var sum = 0L;
        for (var x : nums) sum += x;
        if (sum < k * 2) return 0;
        var ans = 1;
        var f = new int[k];
        f[0] = 1;
        for (var x : nums) {
            ans = ans * 2 % MOD;
            for (var j = k - 1; j >= x; --j)
                f[j] = (f[j] + f[j - x]) % MOD;
        }
        for (var x : f)
            ans = (ans - x * 2 % MOD + MOD) % MOD; // 保证答案非负
        return ans;
    }
}
