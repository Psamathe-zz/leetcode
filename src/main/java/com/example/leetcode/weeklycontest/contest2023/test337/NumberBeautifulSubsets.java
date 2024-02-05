package com.example.leetcode.weeklycontest.contest2023.test337;

/**
 * You are given an array nums of positive integers and a positive integer k.
 *
 * A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
 *
 * Return the number of non-empty beautiful subsets of the array nums.
 *
 * A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,6], k = 2
 * Output: 4
 * Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
 * It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: 1
 * Explanation: The beautiful subset of the array nums is [1].
 * It can be proved that there is only 1 beautiful subset in the array [1].
 * [4,2,5,9,10,3]
 * 1
 */
public class NumberBeautifulSubsets {
    public static void main(String[] args) {
        NumberBeautifulSubsets  numberBeautifulSubsets = new NumberBeautifulSubsets();
        numberBeautifulSubsets.beautifulSubsets(new int[]{4,2,5,9,10,3}, 1);
    }

    private int[] nums, cnt;
    private int k, ans = -1; // 去掉空集

    public int beautifulSubsets(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        cnt = new int[k * 2 + 1001]; // 用数组实现比哈希表更快
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans++;
            return;
        }
        dfs(i + 1); // 不选
        int x = nums[i] + k; // 避免负数下标
        if (cnt[x - k] == 0 && cnt[x + k] == 0) {
            ++cnt[x]; // 选
            dfs(i + 1);
            --cnt[x]; // 恢复现场
        }
    }
}
