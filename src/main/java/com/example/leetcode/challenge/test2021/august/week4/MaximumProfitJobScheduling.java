package com.example.leetcode.challenge.test2021.august.week4;

import java.util.Arrays;
import java.util.Comparator;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 *
 *
 *
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 *
 *
 *
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 */
public class MaximumProfitJobScheduling {
    public static void main(String[] args) {

    }

    /**
     * https://blog.csdn.net/qq_46105170/article/details/118712397
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        int[][] jobs = new int[N][3];
        for (int i = 0; i < N; ++i) {
            jobs[i][0] = endTime[i];
            jobs[i][1] = startTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        int[] f = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            f[i] = f[i - 1];
            int idx = binarySearch(jobs, i - 2, jobs[i - 1][1]);
            f[i] = Math.max(f[i], jobs[i - 1][2] + f[idx + 1]);
        }
        return f[N];
    }

    private int binarySearch(int[][] jobs, int r, int t){
        int l = 0;
        while (l < r){
            int m = l + (r - l + 1 >>  1);
            if(jobs[m][0] <= t) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return jobs[l][0] <= t ? l : -1;
    }
}
