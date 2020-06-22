package com.example.leetcode.medium;

import java.util.Arrays;

/**
 * https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/superpower
 * (a * b) % k = (a % k)(b % k) % k
 *
 *
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
 *
 * Example 1:
 *
 * Input: a = 2, b = [3]
 * Output: 8
 * Example 2:
 *
 * Input: a = 2, b = [1,0]
 * Output: 1024
 *
 * 2147483647
 * [2,0,0]
 */
public class SuperPow {
    public static void main(String[] args) {
        SuperPow superPow = new SuperPow();
        int a = 2147483647;
        int[] b= new int[]{2,0,0};
        int result  = superPow.superPow(a,b);
        System.out.println(result);
    }

    public int superPow(int a, int[] b) {
        return help(a,b);
    }
    int base = 1337;
    int mypow(int a, int k) {
        a %= base;
        int res = 1;
        for (int i = 0; i < k; i++) {
            res *= a;
            res %= base;
        }
        return res;
    }
    public int help(int a, int[] b){
        if(b.length == 0)
            return 1;
        int[] temp = Arrays.copyOfRange(b,0,b.length - 1);
        int s = mypow(help(a,temp),10);
        int p = mypow(a,b[b.length - 1]);
        return   (s * p)%base;
    }

    /**
     * faster solution
     * @param a
     * @param b
     * @return
     */
    public int superPowV2(int a, int[] b) {
        if (a % 1337 == 0) return 0;
        int p = 0;
        for (int i : b) p = (p * 10 + i) % 1140;
        if (p == 0) p += 1440;
        return power(a, p, 1337);
    }
    public int power(int a, int n, int mod) {
        a %= mod;
        int ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) ret = ret * a % mod;
            a = a * a % mod;
            n >>= 1;
        }
        return ret;
    }

    /**
     * less memory
     * @param a
     * @param b
     * @return
     */
    public int superPowV3(int a, int[] b) {
        int power = 1;
        int place = a % 1337;
        for(int i = b.length -1; i >= 0;i--) {
            int last = place;
            for(int j = 1;j < 10;j++) {
                if(j == b[i]) {
                    power = power * place % 1337;
                }
                place = place * last % 1337;
            }
        }
        return power;
    }

}
