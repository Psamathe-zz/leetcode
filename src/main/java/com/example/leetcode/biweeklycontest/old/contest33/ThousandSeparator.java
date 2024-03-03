package com.example.leetcode.biweeklycontest.old.contest33;


/**
 * Given an integer n, add a dot (".") as the thousands separator and return it in string format.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 987
 * Output: "987"
 * Example 2:
 *
 * Input: n = 1234
 * Output: "1.234"
 * Example 3:
 *
 * Input: n = 123456789
 * Output: "123.456.789"
 * Example 4:
 *
 * Input: n = 0
 * Output: "0"
 *
 *
 * Constraints:
 *
 * 0 <= n < 2^31
 */
public class ThousandSeparator {
    public static void main(String[] args) {

    }

    public String thousandSeparator(int n) {
        if(n == 0)
            return String.valueOf(n);
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        int val;
        while (n > 0){
            if(count == 3)
                stringBuilder.append('.');
            count %= 3;
            val = n % 10;
            stringBuilder.append(val);
            count++;
            n = n / 10;
        }
        return stringBuilder.reverse().toString();
    }
}
