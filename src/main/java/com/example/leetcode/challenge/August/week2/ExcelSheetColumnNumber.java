package com.example.leetcode.challenge.August.week2;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * Example 1:
 *
 * Input: "A"
 * Output: 1
 * Example 2:
 *
 * Input: "AB"
 * Output: 28
 * Example 3:
 *
 * Input: "ZY"
 * Output: 701
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 7
 * s consists only of uppercase English letters.
 * s is between "A" and "FXSHRXW".
 */
public class ExcelSheetColumnNumber {
    public static void main(String[] args) {

    }

    public int titleToNumber(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        int length = s.length();
        int val;
        for (int i = 0; i < length; i++) {
            val = chars[i] - 'A' + 1;
            res = res * 26 + val;
        }
        return res;
    }
}
