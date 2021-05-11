package com.example.leetcode.weeklycontest.test239;


/**
 * You are given a string s that consists of only digits.
 *
 * Check if we can split s into two or more non-empty substrings such that the numerical values of the substrings are in descending order and the difference between numerical values of every two adjacent substrings is equal to 1.
 *
 * For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89]. The values are in descending order and adjacent values differ by 1, so this way is valid.
 * Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"]. However all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1] respectively, all of which are not in descending order.
 * Return true if it is possible to split s​​​​​​ as described above, or false otherwise.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1234"
 * Output: false
 * Explanation: There is no valid way to split s.
 * Example 2:
 *
 * Input: s = "050043"
 * Output: true
 * Explanation: s can be split into ["05", "004", "3"] with numerical values [5,4,3].
 * The values are in descending order with adjacent values differing by 1.
 * Example 3:
 *
 * Input: s = "9080701"
 * Output: false
 * Explanation: There is no valid way to split s.
 * Example 4:
 *
 * Input: s = "10009998"
 * Output: true
 * Explanation: s can be split into ["100", "099", "98"] with numerical values [100,99,98].
 * The values are in descending order with adjacent values differing by 1.
 *
 */
public class SplittingString {
    public static void main(String[] args) {
        SplittingString splittingString = new SplittingString();
        boolean res = splittingString.splitString("1234");
        System.out.println(res);
    }

    public boolean splitString(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int first = 0;
        int cur;
        int pre;
        int j;
        boolean goNext;
        for (int i = 0; i < length - 1; i++) {
            if(first == 0 && chars[i] == '0'){
                continue;
            }
            first = first * 10 + (chars[i] - '0');
            pre = first;
            cur = 0;
            j = i + 1;
            goNext = false;
            while (j < length && !goNext){
                if(cur == 0 && chars[j] == '0'){
                    j++;
                    continue;
                }
                cur = cur * 10 + (chars[j] - '0');
                if(cur > pre - 1)
                    goNext = true;
                else if(cur == pre - 1){
                    pre = cur;
                    cur = 0;
                    if(j == length - 1)
                        return true;
                }
                j++;
            }
            if(cur != pre - 1)
                goNext = true;
            if(goNext)
                continue;
            return true;
        }
        return false;
    }
}
