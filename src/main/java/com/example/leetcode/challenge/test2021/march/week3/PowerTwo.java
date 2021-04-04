package com.example.leetcode.challenge.test2021.march.week3;


/**
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Example 2:
 *
 * Input: 10
 * Output: false
 * Example 3:
 *
 * Input: 16
 * Output: true
 * Example 4:
 *
 * Input: 24
 * Output: false
 * Example 5:
 *
 * Input: 46
 * Output: true
 *
 *
 * Note:
 */
public class PowerTwo {
    public static void main(String[] args) {

    }

    /**
     * 直接把这组数字用10的多少次方表示出来；比如N=4654，其中有2个4，1个5，1个6
     * @param N
     * @return
     */
    public boolean reorderedPowerOf2(int N) {
        long ans;
        ans=count(N);
        for (int i=0;i<32;i++) {
            if (count(1<<i)==ans) {
                return true;
            }
        }
        return false;
    }
    private long count(int N) {
        long ans = 0;
        while(N>0) {
            ans+=Math.pow(10,N%10);
            N/=10;
        }
        return ans;
    }
}
