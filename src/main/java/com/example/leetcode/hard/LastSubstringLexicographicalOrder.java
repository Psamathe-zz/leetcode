package com.example.leetcode.hard;

/**
 * Given a string s, return the last substring of s in lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: "bab"
 * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
 * Example 2:
 *
 * Input: "leetcode"
 * Output: "tcode"
 *
 *
 * Note:
 *
 * 1 <= s.length <= 4 * 10^5
 * s contains only lowercase English letters.
 */
public class LastSubstringLexicographicalOrder {
    public static void main(String[] args) {
        String s = "leetcode";
        LastSubstringLexicographicalOrder lastSubstringLexicographicalOrder = new LastSubstringLexicographicalOrder();
        String result = lastSubstringLexicographicalOrder.lastSubstring(s);
        System.out.println(result);
    }

    public String lastSubstring(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int start = 0;
        int newStart = 1;
        int subLength = 0;
        while ( newStart + subLength < length){
            if(chars[start + subLength] - chars[newStart + subLength] == 0){
                subLength++;
                continue;
            } else if(chars[start + subLength] - chars[newStart + subLength] > 0){
                newStart = newStart + subLength + 1;
            } else {
                start = newStart;
                newStart = start + 1;
            }
            subLength = 0;
        }
        return s.substring(start);
    }
}
