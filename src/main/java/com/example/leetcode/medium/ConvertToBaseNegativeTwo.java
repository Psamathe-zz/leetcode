package com.example.leetcode.medium;

/**
 * Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
 *
 * The returned string must have no leading zeroes, unless the string is "0".
 *
 *
 *
 * Example 1:
 *
 * Input: 2
 * Output: "110"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
 * Example 2:
 *
 * Input: 3
 * Output: "111"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
 * Example 3:
 *
 * Input: 4
 * Output: "100"
 * Explantion: (-2) ^ 2 = 4
 *
 *
 * Note:
 *
 * 0 <= N <= 10^9
 */
public class ConvertToBaseNegativeTwo {
    public static void main(String[] args) {
        int N = 3;
        ConvertToBaseNegativeTwo convertToBaseNegativeTwo = new ConvertToBaseNegativeTwo();
        convertToBaseNegativeTwo.baseNeg2V1(N);
    }

    public String baseNeg2(int N) {
        if(N == 0)
            return "0";
        StringBuilder sb = new StringBuilder();

        while(N > 0) {
            int r = N%2;
            sb.append(r);
            if(sb.length()%2 == 0) {
                N = (N+r)/2;
            }
            else {
                N = (N-r)/2;
            }
        }
        return sb.reverse().toString();
    }

    public String baseNeg2V1(int N) {
        if (N == 0)
            return "0";
        return baseNegK(N,-2);
    }

    public String baseNegK(int N,int K) {
        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            int r = N % K;
            N = N / K;
            if (r < 0) {
                r += -K;
                N += 1;
            }
            sb.append(r);
        }
        return sb.reverse().toString();
    }

}
