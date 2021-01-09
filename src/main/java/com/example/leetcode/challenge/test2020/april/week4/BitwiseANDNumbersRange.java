package com.example.leetcode.challenge.test2020.april.week4;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 *
 * Example 1:
 *
 * Input: [5,7]
 * Output: 4
 * Example 2:
 *
 * Input: [0,1]
 * Output: 0
 */
public class BitwiseANDNumbersRange {

    public static void main(String[] args) {
        int m = 5;
        int n = 7;
        BitwiseANDNumbersRange bitwiseANDNumbersRange = new BitwiseANDNumbersRange();
        int result = bitwiseANDNumbersRange.rangeBitwiseAndV3(m,n);
        System.out.println(result);

    }

    public int rangeBitwiseAnd(int m, int n) {
        int result = m;

        for(int i = m + 1; i<= n && i>=0; i++){
            if(result == 0)
                break;
            if(i == Integer.MAX_VALUE)
                break;
            result = result & i;
        }
        return result;
    }


    /**
     * faster solution
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAndV2(int m, int n) {
        int shift = 0;
        while(m < n){ // when equals 0, stop
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }


    /**
     * less memory
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAndV3(int m, int n) {
        long max = (long) n;
        long result = 0;
        //System.out.println(Integer.toBinaryString(2147483646));
        //System.out.println(Integer.toBinaryString(2147483647));

        for (int exp = 0; (max >> exp) > 0; exp++) {
            result |= (1 << exp);
            for (long i = m; i <= n; i++) {
                if (((1 << exp) & i) == 0) {
                    result ^= (1 << exp);
                    break;
                }
            }
        }

        return (int) result;
    }
}
