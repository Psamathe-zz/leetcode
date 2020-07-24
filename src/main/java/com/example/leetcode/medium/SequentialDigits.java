package com.example.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 *
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 *
 *
 *
 * Example 1:
 *
 * Input: low = 100, high = 300
 * Output: [123,234]
 * Example 2:
 *
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 *
 *
 * Constraints:
 *
 * 10 <= low <= high <= 10^9
 */
public class SequentialDigits {
    public static void main(String[] args) {

    }

    public List<Integer> sequentialDigits(int low, int high) {
        String s = "123456789";
        List<Integer> res = new ArrayList<>();
        for (int begin = 0; begin < 9; begin++) {
            for (int len = 1; len <= 9 - begin; len++) {
                String sub = s.substring(begin, begin + len);
                int num = Integer.parseInt(sub);
                if (num >= low && num <= high) {
                    res.add(num);
                }
            }
        }
        Collections.sort(res);
        return res;
    }

}
