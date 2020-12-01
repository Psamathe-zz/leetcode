package com.example.leetcode.biweeklycontest.contest39;


import java.util.*;

/**
 * You are given an array of n integers, nums, where there are at most 50 unique values in the array. You are also given an array of m customer order quantities, quantity, where quantity[i] is the amount of integers the ith customer ordered. Determine if it is possible to distribute nums such that:
 *
 * The ith customer gets exactly quantity[i] integers,
 * The integers the ith customer gets are all equal, and
 * Every customer is satisfied.
 * Return true if it is possible to distribute nums according to the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], quantity = [2]
 * Output: false
 * Explanation: The 0th customer cannot be given two different integers.
 * Example 2:
 *
 * Input: nums = [1,2,3,3], quantity = [2]
 * Output: true
 * Explanation: The 0th customer is given [3,3]. The integers [1,2] are not used.
 * Example 3:
 *
 * Input: nums = [1,1,2,2], quantity = [2,2]
 * Output: true
 * Explanation: The 0th customer is given [1,1], and the 1st customer is given [2,2].
 * Example 4:
 *
 * Input: nums = [1,1,2,3], quantity = [2,2]
 * Output: false
 * Explanation: Although the 0th customer could be given [1,1], the 1st customer cannot be satisfied.
 * Example 5:
 *
 * Input: nums = [1,1,1,1,1], quantity = [2,3]
 * Output: true
 * Explanation: The 0th customer is given [1,1], and the 1st customer is given [1,1,1].
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= 1000
 * m == quantity.length
 * 1 <= m <= 10
 * 1 <= quantity[i] <= 105
 * There are at most 50 unique values in nums.
 */
public class DistributeRepeatingIntegers {
    public static void main(String[] args) {

    }

    /**
     * https://www.bilibili.com/video/BV1Jy4y167xQ?p=4
     * https://leetcode-cn.com/problems/distribute-repeating-integers/solution/xue-xi-bi-ji-fen-xiang-yi-ge-zhuang-tai-dpde-xie-f/
     * @param nums
     * @param quantity
     * @return
     */
    public boolean canDistribute(int[] nums, int[] quantity) {
        int[] cnt = new int[55];
        Arrays.sort(nums);
        int tot = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(i == 0 || nums[i] != nums[i - 1]) cnt[++tot] = 0;
            cnt[tot] += 1;
        }

        int m = quantity.length; // m 位顾客
        int lim = 1 << m;
        boolean[] dp = new boolean[1 << 11]; // 状态压缩动态规划 dp[s] 表示满足顾客状态为 s 是否可行
        dp[0] = true;
        for(int i = 1; i <= tot; i++) { // 使用第 i 个数字满足一些顾客
            // s 从 0 开始遍历可以吗？
            for(int s = lim - 1; s >= 0; s--) { // 当前状态已经满足了 s 的顾客 （一个状态）
                if(dp[s] == false) continue; // dp[s] 集合是要可满足的
                for(int r = 0; r < lim; r++) { // 当前这个数字要满足 r 的顾客 （也是一个状态)
                    if((s & r) > 0) continue; // s 与 r 的顾客不能重叠
                    int sum = 0;
                    for(int k = 0; k < m; k++) {
                        if(((r >> k) & 1) == 1) sum += quantity[k]; // 满足状态 r 的顾客需要多少个数字
                    }
                    if(cnt[i] >= sum) dp[s | r] = true; // 如果当前可以用 i 位数字给顾客，就进行状态转移
                }
            }

        }
        return dp[lim - 1];
    }

    /**
     * other option
     * @param nums
     * @param quantity
     * @return
     */
    public boolean canDistributeV1(int[] nums, int[] quantity) {
        int n = nums.length;
        int m = quantity.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) freq.put(i, freq.getOrDefault(i, 0) + 1);
        List<Integer> freqs = new ArrayList<>(freq.values());
        Collections.sort(freqs);
        Arrays.sort(quantity);

        // dp[i][mask] is true if freqs[0..i] can cover the subset
        boolean[][] dp = new boolean[freqs.size()+1][1 << m];
        dp[0][0] = true;
        for (int i = 1; i <= freqs.size(); i++) {
            int f = freqs.get(i-1);
            for (int j = 0; j < (1 << m); j++) {
                if (dp[i-1][j]) {
                    dp[i][j] = true;
                    continue;
                }

                // Try to use f to cover all possible subsets j
                int s = j;
                while (!dp[i][j] && s > 0) {
                    // use s here
                    int sum = 0;
                    for (int k = 0; k < m; k++) {
                        if ((s & (1 << k)) > 0) sum += quantity[k];
                    }
                    if (sum <= f) {
                        int remaining = j - s;
                        if (dp[i-1][remaining]) dp[i][j] = true;
                    }
                    s = (s-1) & j;
                }
            }
        }
        return dp[freqs.size()][(1 << m) - 1];
    }
}
