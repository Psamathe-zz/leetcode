package com.example.leetcode.biweeklycontest.contest73;

/**
 * You are given a string s consisting only of lowercase English letters.
 *
 * In one move, you can select any two adjacent characters of s and swap them.
 *
 * Return the minimum number of moves needed to make s a palindrome.
 *
 * Note that the input will be generated such that s can always be converted to a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabb"
 * Output: 2
 * Explanation:
 * We can obtain two palindromes from s, "abba" and "baab".
 * - We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
 * - We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
 * Thus, the minimum number of moves needed to make s a palindrome is 2.
 * Example 2:
 *
 * Input: s = "letelt"
 * Output: 2
 * Explanation:
 * One of the palindromes we can obtain from s in 2 moves is "lettel".
 * One of the ways we can obtain it is "letelt" -> "letetl" -> "lettel".
 * Other palindromes such as "tleelt" can also be obtained in 2 moves.
 * It can be shown that it is not possible to obtain a palindrome in less than 2 moves.
 */
public class MinimumNumberMovesToMakePalindrome {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-number-of-moves-to-make-palindrome/solution/tan-xin-shuang-zhi-zhen-by-carter-10-w16m/
     * @param s
     * @return
     */
    public int minMovesToMakePalindrome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int r = n - 1;
        int ans = 0;
        for (int l = 0; l < n / 2; l++) {
            if (chars[l] == chars[r]) {
                r--;
                continue;
            }
            int pos = r - 1;
            for (; pos > l; pos--) {
                if (chars[l] == chars[pos]) {
                    System.arraycopy(chars, pos + 1, chars, pos, r - pos);
                    ans += r - pos;
                    break;
                }
            }
            if (pos == l) {
                //没有找到匹配的，需要放在中间，这里直接增加次数然后跳过
                ans += n / 2 - l;
            } else {
                r--;
            }
        }
        return ans;
    }
}
