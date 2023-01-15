package com.example.leetcode.weeklycontest.test326;

/**
 * Given two positive integers left and right, find the two integers num1 and num2 such that:
 *
 * left <= nums1 < nums2 <= right .
 * nums1 and nums2 are both prime numbers.
 * nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
 * Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs satisfying these conditions, return the one with the minimum nums1 value or [-1, -1] if such numbers do not exist.
 *
 * A number greater than 1 is called prime if it is only divisible by 1 and itself.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 10, right = 19
 * Output: [11,13]
 * Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
 * The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
 * Since 11 is smaller than 17, we return the first pair.
 * Example 2:
 *
 * Input: left = 4, right = 6
 * Output: [-1,-1]
 * Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.
 */
public class ClosestPrimeNumbersRange {

    /**
     * https://leetcode.cn/problems/closest-prime-numbers-in-range/solution/yu-chu-li-zhi-shu-mei-ju-by-endlesscheng-uw2b/
     */
    private final static int MX = (int) 1e6;
    private final static int[] primes = new int[78500];

    static {
        var np = new boolean[MX + 1];
        var pi = 0;
        for (var i = 2; i <= MX; ++i)
            if (!np[i]) {
                primes[pi++] = i;
                for (var j = i; j <= MX / i; ++j) // 避免溢出的写法
                    np[i * j] = true;
            }
        primes[pi++] = MX + 1;
        primes[pi++] = MX + 1; // 保证下面下标不会越界
    }

    public int[] closestPrimes(int left, int right) {
        int p = -1, q = -1;
        for (var i = lowerBound(primes, left); primes[i + 1] <= right; ++i)
            if (p < 0 || primes[i + 1] - primes[i] < q - p) {
                p = primes[i];
                q = primes[i + 1];
            }
        return new int[]{p, q};
    }

    // 见 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right;
    }

}
