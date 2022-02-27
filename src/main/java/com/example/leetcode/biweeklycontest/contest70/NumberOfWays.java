package com.example.leetcode.biweeklycontest.contest70;

/**
 * Along a long library corridor, there is a line of seats and decorative plants. You are given a 0-indexed string corridor of length n consisting of letters 'S' and 'P' where each 'S' represents a seat and each 'P' represents a plant.
 *
 * One room divider has already been installed to the left of index 0, and another to the right of index n - 1. Additional room dividers can be installed. For each position between indices i - 1 and i (1 <= i <= n - 1), at most one divider can be installed.
 *
 * Divide the corridor into non-overlapping sections, where each section has exactly two seats with any number of plants. There may be multiple ways to perform the division. Two ways are different if there is a position with a room divider installed in the first way but not in the second way.
 *
 * Return the number of ways to divide the corridor. Since the answer may be very large, return it modulo 109 + 7. If there is no way, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: corridor = "SSPPSPS"
 * Output: 3
 * Explanation: There are 3 different ways to divide the corridor.
 * The black bars in the above image indicate the two room dividers already installed.
 * Note that in each of the ways, each section has exactly two seats.
 * Example 2:
 *
 *
 * Input: corridor = "PPSPSP"
 * Output: 1
 * Explanation: There is only 1 way to divide the corridor, by not installing any additional dividers.
 * Installing any would create some section that does not have exactly two seats.
 * Example 3:
 *
 *
 * Input: corridor = "S"
 * Output: 0
 * Explanation: There is no way to divide the corridor because there will always be a section that does not have exactly two seats.
 */
public class NumberOfWays {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/number-of-ways-to-divide-a-long-corridor/solution/java-yi-liang-ge-zuo-wei-wei-qi-shi-biao-5ubp/
     * @param corridor
     * @return
     */
    public int numberOfWays(String corridor) {
        long ans = 1;
        int n = corridor.length();

        int sumS = 0;
        int curS = 0;
        for (int i = 0; i < n; i++) {
            // 如果前面有2个座位了，计算后面连续的植物个数
            if (curS == 2) {
                int curP = 0;
                while (i < n && corridor.charAt(i) == 'P') {
                    curP++;
                    i++;
                }

                // 计算完毕后发现后面还有座位，则先把当前累计的方案记下来。
                if (i < n) {
                    ans = (ans * (curP + 1)) % 1000000007;
                    curS = 0;
                }
                // 如果后面没有座位了，那么不计算这次统计的连续的植物个数
                else {
                    break;
                }
            }

            // 统计座位的个数
            if (corridor.charAt(i) == 'S') {
                curS++;
                sumS++;
            }
        }
        // 没座位或者座位的个数为奇数则不能分割
        if (sumS == 0 || sumS % 2 != 0) return 0;
        return (int) ans;
    }
}
