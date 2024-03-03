package com.example.leetcode.biweeklycontest.old.contest53;


import java.util.HashSet;
import java.util.Set;

/**
 * A string is good if there are no repeated characters.
 *
 * Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.
 *
 * Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "xyzzaz"
 * Output: 1
 * Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
 * The only good substring of length 3 is "xyz".
 * Example 2:
 *
 * Input: s = "aababcabc"
 * Output: 4
 * Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
 * The good substrings are "abc", "bca", "cab", and "abc".
 */
public class SubstringsSizeThree {
    public static void main(String[] args) {
        SubstringsSizeThree substringsSizeThree = new SubstringsSizeThree();
        substringsSizeThree.countGoodSubstrings("xyzzaz");
    }

    public int countGoodSubstrings(String s) {
        int res = 0;
        int length = s.length();
        if(length < 3)
            return 0;

        int a, b, c;
        for (int i = 2; i < length; i++) {
            a = s.charAt(i-2) - 'a';
            b = s.charAt(i-1) - 'a';
            c = s.charAt(i) - 'a';
            if(isValid(a, b , c))
                res++;
        }
        return res;
    }

    public boolean isValid(int a, int b, int c){
        return a != b && b!= c && a != c;
    }
}
