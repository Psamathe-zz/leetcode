package com.example.leetcode.weeklycontest.test248;

/**
 * A digit string is good if the digits (0-indexed) at even indices are even and the digits at odd indices are prime (2, 3, 5, or 7).
 *
 * For example, "2582" is good because the digits (2 and 8) at even positions are even and the digits (5 and 2) at odd positions are prime. However, "3245" is not good because 3 is at an even index but is not even.
 * Given an integer n, return the total number of good digit strings of length n. Since the answer may be large, return it modulo 109 + 7.
 *
 * A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".
 * Example 2:
 *
 * Input: n = 4
 * Output: 400
 * Example 3:
 *
 * Input: n = 50
 * Output: 564908303
 */
public class CountGoodNumbers {
    public static void main(String[] args) {
        CountGoodNumbers countGoodNumbers = new CountGoodNumbers();
        countGoodNumbers.countGoodNumbers(50);
    }

    /**
     * https://zhuanlan.zhihu.com/p/95902286
     * * 非递归快速幂
     * 我们换一个角度来引入非递归的快速幂。还是7的10次方，但这次，我们把10写成二进制的形式，也就是 [公式] 。
     *
     * 现在我们要计算 [公式] ，可以怎么做？我们很自然地想到可以把它拆分为 [公式] . 实际上，对于任意的整数，我们都可以把它拆成若干个 [公式] 的形式相乘。而这些[公式]，恰好就是 [公式] 、[公式]、[公式]……我们只需不断把底数平方就可以算出它们。
     *
     * 我们先看代码，再来仔细推敲这个过程：
     * @param n
     * @return
     */
    public int countGoodNumbers(long n) {
        long base=20,res=n%2==0?1:5,power=n/2;
        int MOD=1000000007;
        while(power>0){
            if(power%2==1){
                res =res*base%MOD;
            }
            base =(base*base)%MOD;
            power/=2;
        }
        return (int)res;
    }
}
