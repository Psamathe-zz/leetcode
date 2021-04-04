package com.example.leetcode.weeklycontest.test235;


/**
 * You are given an array nums that consists of positive integers.
 *
 * The GCD of a sequence of numbers is defined as the greatest integer that divides all the numbers in the sequence evenly.
 *
 * For example, the GCD of the sequence [4,6,16] is 2.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 *
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * Return the number of different GCDs among all non-empty subsequences of nums.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [6,10,3]
 * Output: 5
 * Explanation: The figure shows all the non-empty subsequences and their GCDs.
 * The different GCDs are 6, 10, 3, 2, and 1.
 * Example 2:
 *
 * Input: nums = [5,15,40,5,6]
 * Output: 7
 */
public class NumberDifferentSubsequencesGCDs {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/number-of-different-subsequences-gcds/solution/mei-ju-mei-ge-ke-neng-de-gong-yue-shu-by-rsd3/
     * @param nums
     * @return
     */
    public int countDifferentSubsequenceGCDs(int[] nums) {
        boolean[] visited = new boolean[200005];
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            visited[num] = true;
            max = Math.max(max, num);
        }
        int count = 0;
        // 公约数可能的范围【1, max】
        for (int i = 1; i <= max; i++) {
            int commonGCD = -1;
            // 检查所有i的倍数
            for (int j = i; j <= max; j += i) {
                if (visited[j]) {
                    if (commonGCD == -1) {
                        commonGCD = j;
                    } else {
                        commonGCD = gcd(commonGCD, j);
                    }
                }
            }
            if (i == commonGCD) {
                count++;
            }
        }
        return count;
    }

    private int gcd(int x, int y) {
        if (x == 0) {
            return y;
        }
        return gcd(y % x, x);
    }

}
