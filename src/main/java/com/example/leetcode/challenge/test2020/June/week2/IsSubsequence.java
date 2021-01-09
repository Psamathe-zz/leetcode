package com.example.leetcode.challenge.test2020.June.week2;

/**
 * Given a string s and a string t, check if s is subsequence of t.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 *
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * Both strings consists only of lowercase characters.
 * ""
 * "ahbgdc"
 * */
public class IsSubsequence {
    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";

        IsSubsequence isSubsequence = new IsSubsequence();
        boolean result = isSubsequence.isSubsequenceV1(s,t);
        System.out.println(result);
    }

    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0)
            return true;
        if(s.length() > t.length())
            return false;
        int indexS = 0;
        int indexT = 0;
        while (indexT < t.length()){
            if(s.charAt(indexS) == t.charAt(indexT)){
                indexS++;
            }
            if(indexS == s.length()){
                return true;
            }
            indexT++;

        }
        return false;
    }

    /**
     * faster solution
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequenceV1(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()){
            index = t.indexOf(c, index + 1);
            if (index == -1)
                return false;
        }
        return true;
    }
}
