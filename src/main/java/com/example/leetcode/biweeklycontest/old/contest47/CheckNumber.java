package com.example.leetcode.biweeklycontest.old.contest47;


/**
 * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
 *
 * An integer y is a power of three if there exists an integer x such that y == 3x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: true
 * Explanation: 12 = 31 + 32
 * Example 2:
 *
 * Input: n = 91
 * Output: true
 * Explanation: 91 = 30 + 32 + 34
 * Example 3:
 *
 * Input: n = 21
 * Output: false
 *
 */
public class CheckNumber {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/check-if-number-is-a-sum-of-powers-of-three/solution/di-gui-fen-lei-by-hu-li-hu-wai-3s03/
     * @param n
     * @return
     */
    public boolean checkPowersOfThree(int n) {
        if(n<=0)return false;
        if(n==1)return true;
        if(n%3==0) return checkPowersOfThree(n/3);
        else if (n % 3 ==1)return checkPowersOfThree((n-1)/3);
        return false;
    }
}
