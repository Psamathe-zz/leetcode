package com.example.leetcode.weeklycontest.old.test207;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 *
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 * Example 3:
 *
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 *
 * s contains only lower case English letters.
 */
public class SplitStringIntoMaxNumberUniqueSubstrings {
    public static void main(String[] args) {

    }

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        int res = 0;
        char[] chars = s.toCharArray();
        int length = s.length();
        int[] count = new int[length];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
            if(!set.contains(stringBuilder.toString())){
                set.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                res++;
            }
        }
        return res;
    }
}
