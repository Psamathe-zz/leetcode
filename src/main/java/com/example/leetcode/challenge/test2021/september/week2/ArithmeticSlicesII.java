package com.example.leetcode.challenge.test2021.september.week2;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums, return the number of all the arithmetic subsequences of nums.
 *
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
 * For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 *
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * The test cases are generated so that the answer fits in 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,6,8,10]
 * Output: 7
 * Explanation: All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 * Example 2:
 *
 * Input: nums = [7,7,7,7,7]
 * Output: 16
 * Explanation: Any subsequence of this array is arithmetic.
 */
public class ArithmeticSlicesII {
    public static void main(String[] args) {

    }

    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        // dp[i]：以 nums[i] 结尾，公差为 key 的长度大于等于 2 的等差数列的个数
        Map<Integer, Integer>[] dp = new HashMap[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new HashMap<>();
        }

        int res = 0;
        // 从 1 开始就可以
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                // dp[i][diff] += (dp[j][diff] + 1) ，Java 写起来有点麻烦，表示 nums[i] 可以接在之前「公差相等」的等差数列后面形成长度更长的等差数列
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + dp[j].getOrDefault(diff, 0) + 1);

                // 与之前的等差数列公差相等的时候，说明可以接上，此时计算结果
                if (dp[j].containsKey(diff)) {
                    // 理解：对结果的贡献「恰好是」之前的某个 j 的对应状态值，这里的 j 一定会在之前的某一个 i 加上 1，看上面有注释的那一行代码
                    res += dp[j].get(diff);
                }
            }
        }
        return res;
    }


    public int numberOfArithmeticSlicesV1(int[] nums) {
        int ans = 0;
        int n = nums.length;
        Map<Long, Integer>[] f = new Map[n];
        for (int i = 0; i < n; ++i) {
            f[i] = new HashMap<Long, Integer>();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long d = 1L * nums[i] - nums[j];
                int cnt = f[j].getOrDefault(d, 0);
                ans += cnt;
                f[i].put(d, f[i].getOrDefault(d, 0) + cnt + 1);
            }
        }
        return ans;
    }
}
