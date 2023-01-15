package com.example.leetcode.biweeklycontest.contest95;

/**
 * You are given a 0-indexed integer array stations of length n, where stations[i] represents the number of power stations in the ith city.
 *
 * Each power station can provide power to every city in a fixed range. In other words, if the range is denoted by r, then a power station at city i can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.
 *
 * Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
 * The power of a city is the total number of power stations it is being provided power from.
 *
 * The government has sanctioned building k more power stations, each of which can be built in any city, and have the same range as the pre-existing ones.
 *
 * Given the two integers r and k, return the maximum possible minimum power of a city, if the additional power stations are built optimally.
 *
 * Note that you can build the k power stations in multiple cities.
 *
 *
 *
 * Example 1:
 *
 * Input: stations = [1,2,4,5,0], r = 1, k = 2
 * Output: 5
 * Explanation:
 * One of the optimal ways is to install both the power stations at city 1.
 * So stations will become [1,4,4,5,0].
 * - City 0 is provided by 1 + 4 = 5 power stations.
 * - City 1 is provided by 1 + 4 + 4 = 9 power stations.
 * - City 2 is provided by 4 + 4 + 5 = 13 power stations.
 * - City 3 is provided by 5 + 4 = 9 power stations.
 * - City 4 is provided by 5 + 0 = 5 power stations.
 * So the minimum power of a city is 5.
 * Since it is not possible to obtain a larger power, we return 5.
 * Example 2:
 *
 * Input: stations = [4,4,4,4], r = 0, k = 3
 * Output: 4
 * Explanation:
 * It can be proved that we cannot make the minimum power of a city greater than 4.
 */
public class MaximizeTheMinimumPoweredCity {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/maximize-the-minimum-powered-city/solution/er-fen-da-an-qian-zhui-he-chai-fen-shu-z-jnyv/
     * @param stations
     * @param r
     * @param k
     * @return
     */
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] sum = new long[n + 1]; // 前缀和
        for (int i = 0; i < n; ++i)
            sum[i + 1] = sum[i] + stations[i];
        long mn = Long.MAX_VALUE;
        long[] power = new long[n]; // 电量
        for (int i = 0; i < n; ++i) {
            power[i] = sum[Math.min(i + r + 1, n)] - sum[Math.max(i - r, 0)];
            mn = Math.min(mn, power[i]);
        }

        long left = mn, right = mn + k + 1; // 开区间写法
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (check(mid, power, n, r, k)) left = mid;
            else right = mid;
        }
        return left;
    }

    private boolean check(long minPower, long[] power, int n, int r, int k) {
        long[] diff = new long[n + 1]; // 差分数组
        long sumD = 0, need = 0;
        for (int i = 0; i < n; ++i) {
            sumD += diff[i]; // 累加差分值
            long m = minPower - power[i] - sumD;
            if (m > 0) { // 需要 m 个供电站
                need += m;
                if (need > k) return false; // 提前退出这样快一些
                sumD += m; // 差分更新
                if (i + r * 2 + 1 < n) diff[i + r * 2 + 1] -= m; // 差分更新
            }
        }
        return true;
    }
}
