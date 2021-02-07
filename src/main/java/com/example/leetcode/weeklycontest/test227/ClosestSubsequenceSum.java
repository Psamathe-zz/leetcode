package com.example.leetcode.weeklycontest.test227;


import java.util.Arrays;

/**
 * You are given an integer array nums and an integer goal.
 *
 * You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal. That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
 *
 * Return the minimum possible value of abs(sum - goal).
 *
 * Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,-7,3,5], goal = 6
 * Output: 0
 * Explanation: Choose the whole array as a subsequence, with a sum of 6.
 * This is equal to the goal, so the absolute difference is 0.
 * Example 2:
 *
 * Input: nums = [7,-9,15,-2], goal = -5
 * Output: 1
 * Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
 * The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
 * Example 3:
 *
 * Input: nums = [1,2,3], goal = -7
 * Output: 7
 */
public class ClosestSubsequenceSum {
    public static void main(String[] args) {

    }



    /**
     * https://leetcode-cn.com/problems/closest-subsequence-sum/solution/er-fen-javashi-xian-by-zzq-_-56rba/
     * 构建字典序较大的字符串， 依次从给定的两个字符串 a b 中取走第一个， 谁得较大， 就先取谁的。
     *
     * 那么问题就在如果 a、b 两个字符串的第一个字符相同时， 该取谁的？ 很容易想到是比较下一个，看谁大，就取谁的，然后这个相同的字符可能会连续很多个。
     *
     * 比较简单的就是在遍历a、b 的当前位置开始截取往后的字符串c、d，然后比较c、d 的字符串谁大， 那就先取较大的那个字符串的就ok
     *
     * @param nums
     * @param goal
     * @return
     */
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        int n1 = (n + 1) / 2, n2 = n - n1;
        int[] f1 = new int[(1 << n1)], f2 = new int[(1 << n2)];
        for (int i = 0; i < (1 << n1); ++i) {       //前半部分所有组合
            for (int j = 0; j < n1; ++j) {
                if (((i >> j) & 1) == 1) {
                    f1[i] += nums[j];
                }
            }
        }
        for (int i = 0; i < (1 << n2); ++i) {       //后半部分所有组合
            for (int j = 0; j < n2; ++j) {
                if (((i >> j) & 1) == 1) {
                    f2[i] += nums[n1 + j];
                }
            }
        }
        Arrays.sort(f2);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < f1.length; ++i) {       //枚举f1
            int target = goal - f1[i];
            int index = lower_bound(f2, target);    //二分查找f2
            if (index < f2.length) {
                ans = Math.min(ans, Math.abs(f1[i] + f2[index] - goal));
            }
            if (index - 1 >= 0) {
                ans = Math.min(ans, Math.abs(f1[i] + f2[index - 1] - goal));
            }
        }
        return ans;
    }

    static int lower_bound(int[] g, int target) {
        /**
         * g中搜索≥target的第一个数字的位置[l,r)
         * */
        int l = 0, r = g.length;
        //if(target>g[r-1])return r;
        while (l + 1 < r) {
            int mid = l + (r - 1 - l) / 2;
            if (g[mid] < target) {
                l = mid + 1;
            } else if (g[mid] >= target) {
                r = mid + 1;
            }
        }
        return l;
    }

}
