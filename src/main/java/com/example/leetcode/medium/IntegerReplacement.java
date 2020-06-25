package com.example.leetcode.medium;

import java.util.Arrays;

/**Given a positive integer n and you can do operations as follow:

 If n is even, replace n with n/2.
 If n is odd, you can replace n with either n + 1 or n - 1.
 What is the minimum number of replacements needed for n to become 1?

 Example 1:

 Input:
 8

 Output:
 3

 Explanation:
 8 -> 4 -> 2 -> 1
 Example 2:

 Input:
 7

 Output:
 4

 Explanation:
 7 -> 8 -> 4 -> 2 -> 1
 or
 7 -> 6 -> 3 -> 2 -> 1
 *
 * https://www.cnblogs.com/grandyang/p/5873525.html
 */
public class IntegerReplacement {
    public static void main(String[] args) {
        IntegerReplacement integerReplacement = new IntegerReplacement();
        int result = integerReplacement.integerReplacement(7);
        System.out.println(result);
    }

    public int integerReplacement(int n) {
        if (n == 1) return 0;
        if (n % 2 == 0)
            return 1 + integerReplacement(n / 2);
        else {
            long t = n;
            long plus = (t + 1) / 2;
            long minus = (t - 1) / 2;
            return 2 + Math.min(integerReplacement((int) plus), integerReplacement((int)minus));
        }
    }

    int integerReplacementV1(int n) {
        long t = n;
        int cnt = 0;
        while (t > 1) {
            ++cnt;
            if (t % 2 == 1) {
                if ((t + 1) % 4 == 0 && (t != 3))
                    ++t;
                else --t;
            } else {
                t >>= 1;
            }
        }
        return cnt;
    }


}
