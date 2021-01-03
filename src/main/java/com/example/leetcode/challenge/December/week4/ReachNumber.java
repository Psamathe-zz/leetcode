package com.example.leetcode.challenge.December.week4;


/**
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 *
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 *
 * Return the minimum number of steps required to reach the destination.
 *
 * Example 1:
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second step we step from 1 to 3.
 * Example 2:
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second move we step  from 1 to -1.
 * On the third move we step from -1 to 2.
 * Note:
 * target will be a non-zero integer in the range [-10^9, 10^9].
 */
public class ReachNumber {
    public static void main(String[] args) {

    }

    public int reachNumber(int target) {
        target = Math.abs(target);
        int res = 0, sum = 0;
        while (sum < target || (sum - target) % 2 == 1) {
            ++res;
            sum += res;
        }
        return res;
    }

    int reachNumberV1(int target) {
        double n = Math.ceil((Math.sqrt(1 + 8.0 * Math.abs(target)) - 1) / 2), d = n * (n + 1) / 2 - target;
        return (int)(n + (d % 2) * (n % 2 + 1));
    }
}
