package com.example.leetcode.challenge.september.week3;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 *
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 *
 * Could you do this in O(n) runtime?
 *
 * Example:
 *
 * Input: [3, 10, 5, 25, 2, 8]
 *
 * Output: 28
 *
 * Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class MaximumXORTwoNumbersArray {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/grandyang/p/5991530.html
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int res = 0, mask = 0;
        Set<Integer> s;
        for (int i = 31; i >= 0; --i) {
            mask |= (1 << i);
            s = new HashSet<>();
            for (int num : nums) {
                s.add(num & mask);
            }
            int t = res | (1 << i);
            for (int prefix : s) {
                if (s.contains(t ^ prefix)) {
                    res = t;
                    break;
                }
            }
        }
        return res;
    }
}
