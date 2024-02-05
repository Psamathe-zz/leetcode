package com.example.leetcode.weeklycontest.old.test223;


import java.util.Arrays;

/**
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
 *
 * There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
 *
 * Return the minimum possible maximum working time of any assignment.
 *
 *
 *
 * Example 1:
 *
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 * Example 2:
 *
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 */
public class FindMinimumTime {

    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/solution/java-zhuang-ya-zi-ji-mei-ju-by-zanyjoker-fv42/
     * https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/solution/zhuang-ya-dp-jing-dian-tao-lu-xin-shou-j-3w7r/
     * 设 \textit{jobs}jobs 的长度为 NN，则可以用一个 [0,2^N][0,2
     * N
     *  ] 之间的整数代表 \textit{jobs}jobs 的任意一个子集。下文中，我们不加区分地用 ii 表示「整数 ii」或者「整数 ii 对应的子集」
     *
     * 于是，令 \textit{tot}[i]tot[i] 代表子集 ii 的工作总时间。设子集 ii 的其中（任意）一个元素为 jj，则 i-(1<<j)i−(1<<j) 代表了「子集 ii 中去掉了元素 jj 后剩下的那部分」。因此，我们有
     *
     * \textit{tot}[i] = \textit{tot}[i-(1<<j)] + \textit{jobs}[j]
     * tot[i]=tot[i−(1<<j)]+jobs[j]
     *
     * 随后，我们设 \textit{dp}[j][i]dp[j][i] 表示：前 jj 个工人为了完成作业子集 ii，需要花费的最大工作时间的最小值。
     *
     * 由于前 jj 个工人完成了作业子集 ii，因此第 jj 个工人必然完成 ii 的某个子集 ss，而其余的工人完成子集 i-si−s。对于特定的 ss 而言，前 j-1j−1 个工人完成子集 i-si−s 需要花费的最大工作时间的最小值为 dp[j-1][i-s]dp[j−1][i−s]，而工人 jj 的工作时间为 \textit{tot}[s]tot[s]，故此时总的最大工作时间的最小值为 \max\{dp[j-1][i-s], \textit{tot}[s]\}max{dp[j−1][i−s],tot[s]}。
     *
     * 因此，根据题意，我们遍历所有的子集 ss ，并求出全局的最小值，即
     *
     * \textit{dp}[j][i] = \min_{s \subseteq i}(\max\{dp[j-1][i-s], \textit{tot}[s]\})
     * dp[j][i]=
     * s⊆i
     * min
     * ​
     *  (max{dp[j−1][i−s],tot[s]})
     *
     * 最终，\textit{dp}[k-1][(1<<N)-1]dp[k−1][(1<<N)−1] 即为我们所求的答案。
     *
     * @param jobs
     * @param k
     * @return
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[][] f = new int[k + 1][1 << n];
        for(int i = 0; i <= k; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE / 2);
        }
        f[0][0] = 0;
        int[] sum = new int[1 << n];
        for(int i = 0; i < (1 << n); ++i) {
            for(int j = 0; j < n; ++j) {
                if(((i >> j) & 1) > 0) {
                    sum[i] += jobs[j];
                }
            }
        }
        for(int i = 1; i <= k; ++i) {
            for(int s = 0; s < (1 << n); ++s) {
                for(int ns = s; ns > 0; ns = (ns - 1) & s) {
                    f[i][s] = Math.min(f[i][s], Math.max(f[i - 1][s ^ ns], sum[ns]));
                }
            }
        }
        return f[k][(1 << n) - 1];
    }
}
