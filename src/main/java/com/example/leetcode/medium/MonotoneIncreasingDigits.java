package com.example.leetcode.medium;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
 *
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
 *
 * Example 1:
 * Input: N = 10
 * Output: 9
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 * Example 3:
 * Input: N = 332
 * Output: 299
 * Note: N is an integer in the range [0, 10^9].
 */
public class MonotoneIncreasingDigits {
    public static void main(String[] args) {
        int N = 10;
        MonotoneIncreasingDigits monotoneIncreasingDigits = new MonotoneIncreasingDigits();
        int result  = monotoneIncreasingDigits.monotoneIncreasingDigitsV2(N);
        System.out.println(result);

    }

    public int monotoneIncreasingDigits(int N) {

        if(Integer.valueOf(Arrays.stream(String.valueOf(N).split("")).sorted().collect(Collectors.joining())) == N)
            return N;
        char[] chars = String.valueOf(N).toCharArray();

        int index = 0;
        while(index+1 < chars.length && Integer.valueOf(chars[index]) <=  Integer.valueOf(chars[index+1]) ){
            index++;
        }
        for(int i =chars.length - 1; i >= 0; i--){
            if(i>index){
                chars[i] = '9';
            }else{
                chars[i] = Character.forDigit(Integer.parseInt(String.valueOf(chars[i])) - 1,10);
                if(Arrays.stream(String.valueOf(chars).split("")).sorted().collect(Collectors.joining()).equals(String.valueOf(chars))){
                    break;
                }
                chars[i] = '9';
            }

        }
        return Integer.valueOf(String.valueOf(chars).trim());
    }

    /**
     * faster solution
     * @param n
     * @return
     */
    public int monotoneIncreasingDigitsV2(int n) {
        if (n < 10) {
            return n;
        }
        int[] digits = new int[String.valueOf(n).length()];
        int idx = 0;
        while (n > 0) {
            digits[idx++] = n % 10;  // stored from less significat to most significat
            n /= 10;
        }
        for (int i = 0; i < digits.length - 1; ++i) {
            if (digits[i] < digits[i + 1]) {
                digits[i + 1] = digits[i + 1] - 1;
                for (int j = 0; j <= i; ++j)
                    digits[j] = 9;
            }
        }
        int res = 0;
        for (int i = digits.length - 1; i >= 0; --i) {
            res *= 10;
            res += digits[i];
        }
        return res;
    }


}
