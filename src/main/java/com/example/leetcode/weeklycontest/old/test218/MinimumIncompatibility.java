package com.example.leetcode.weeklycontest.old.test218;


import java.util.Arrays;

/**
 * You are given an integer array nums​​​ and an integer k. You are asked to distribute this array into k subsets of equal size such that there are no two equal elements in the same subset.
 *
 * A subset's incompatibility is the difference between the maximum and minimum elements in that array.
 *
 * Return the minimum possible sum of incompatibilities of the k subsets after distributing the array optimally, or return -1 if it is not possible.
 *
 * A subset is a group integers that appear in the array with no particular order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,4], k = 2
 * Output: 4
 * Explanation: The optimal distribution of subsets is [1,2] and [1,4].
 * The incompatibility is (2-1) + (4-1) = 4.
 * Note that [1,1] and [2,4] would result in a smaller sum, but the first subset contains 2 equal elements.
 * Example 2:
 *
 * Input: nums = [6,3,8,1,3,1,2,2], k = 4
 * Output: 6
 * Explanation: The optimal distribution of subsets is [1,2], [2,3], [6,8], and [1,3].
 * The incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
 * Example 3:
 *
 * Input: nums = [5,3,3,6,3,3], k = 3
 * Output: -1
 * Explanation: It is impossible to distribute nums into 3 subsets where no two elements are equal in the same subset.
 *
 */
public class MinimumIncompatibility {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-incompatibility/solution/lao-tao-lu-zhuang-tai-ya-suo-dp-by-newha-j58b/
     * 预处理
     * 对 \texttt{nums}nums 进行从小到大排序。
     * 预先处理 k = 1k=1 和 k = nk=n 的特殊情况。
     * 计算出每个子集的大小：\texttt{per = n / k}per = n / k。
     * 状态的定义
     * 定义 \texttt{dp[mask][pre]}dp[mask][pre] ：
     *
     * \texttt{mask - }mask -  当前哪些数用了，不能再用（二进制位为 00），那些数字可用（二进制位为 11)
     * \texttt{pre - }pre -  上一次选择的数字是 \texttt{nums}nums 的第 \texttt{pre}pre 个。
     * 状态的转移
     * 我们一个子集一个子集地分配数字：分配完一个子集后，再分配下一个子集；在每个子集，我们 从小到大 分配数字。
     *
     * 我们可以通过 \texttt{mask}mask 的二进制表示中的 11 的个数来获得当前可用的数字个数 \texttt{cnt = \_\_builtin\_popcount(mask)}cnt = __builtin_popcount(mask)。
     *
     * 如果当前 \texttt{cnt}cnt 不能被子集的大小 \texttt{per}per 整除，那么我们在选下一个数字时，则需要考虑上一个数字 \texttt{nums[pre]}nums[pre] 的影响。为了不出现重复数字，且我们从小到大选择数字，因此我们只能选择可用的、比 \texttt{nums[pre]}nums[pre] 大的数字。设我们选择了 \texttt{nums[p]}nums[p]，则新增加的不兼容度 = \texttt{nums[p] - nums[pre]}nums[p] - nums[pre]，如下图所示：
     *
     *
     * 转移方程为：
     *
     * \texttt{if (mask \& (1<<p)) == 1 and nums[p] > nums[pre]:}
     * if (mask & (1<<p)) == 1 and nums[p] > nums[pre]:
     *
     * \texttt{dp[mask][pre] = min(dp[mask][p], dp[mask-(1<<p)][p] + nums[p]-nums[pre])}
     * dp[mask][pre] = min(dp[mask][p], dp[mask-(1<<p)][p] + nums[p]-nums[pre])
     *
     * 如果当前 \texttt{cnt}cnt 能被子集的大小 \texttt{per}per 整除，代表着我们在给一个新的子集分配，这时 \texttt{pre}pre 变量无关紧要，我们只需要选择当前子集的第一个数字即可：
     *
     * \texttt{if (mask \& (1<<p)) == 1:}
     * if (mask & (1<<p)) == 1:
     *
     * \texttt{dp[mask][0] = ... = dp[mask][n-1] = min(dp[mask][0], dp[mask-(1<<p)][p])}
     * dp[mask][0] = ... = dp[mask][n-1] = min(dp[mask][0], dp[mask-(1<<p)][p])
     *
     * 复杂度分析
     * 时间复杂度：O(2^{n}\times n^{2})O(2
     * n
     *  ×n
     * 2
     *  )
     * 空间复杂度：O(2^{n}\times n)O(2
     * n
     *  ×n)
     *
     * @param nums
     * @param k
     * @return
     */
    int INF = Integer.MAX_VALUE;
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[1 << n];
        if (k == n) return 0;
        Arrays.fill(dp, INF);
        int m = n / k;

        for (int mask = 1; mask < (1 << n); mask++){
            int bc = Integer.bitCount(mask);
            if (bc == m){
                int[] freq = new int[n + 1];
                boolean flag = true;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < n; i++){
                    if ((mask & (1 << i) )!= 0){
                        min = Math.min(min, nums[i]);
                        max = Math.max(max, nums[i]);
                        if (++freq[nums[i]] > 1){
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag){
                    dp[mask] = max - min;
                }
            }
            else if (bc % m == 0){
                for (int j = mask; j != 0; j = ((j - 1) & mask)){
                    if (dp[j ^ mask] != INF &&
                            dp[j] != INF){
                        dp[mask] = Math.min(dp[mask], dp[mask ^ j] + dp[j]);
                    }
                }
            }
        }
        return dp[(1 << n) - 1] == INF ? - 1 : dp[(1 << n) - 1];
    }

}
