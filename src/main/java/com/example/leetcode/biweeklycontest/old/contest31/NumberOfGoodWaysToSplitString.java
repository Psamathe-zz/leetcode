package com.example.leetcode.biweeklycontest.old.contest31;

/**
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.
 *
 * Return the number of good splits you can make in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aacaba"
 * Output: 2
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: 1
 * Explanation: Split the string as follows ("ab", "cd").
 * Example 3:
 *
 * Input: s = "aaaaa"
 * Output: 4
 * Explanation: All possible splits are good.
 * Example 4:
 *
 * Input: s = "acbadbaada"
 * Output: 2
 *
 *
 * Constraints:
 *
 * s contains only lowercase English letters.
 * 1 <= s.length <= 10^5
 */
public class NumberOfGoodWaysToSplitString {
    public static void main(String[] args) {
        String s = "aacaba";
        NumberOfGoodWaysToSplitString numberOfGoodWaysToSplitString = new NumberOfGoodWaysToSplitString();
        numberOfGoodWaysToSplitString.numSplits(s);
    }

    public int numSplits(String s) {
        int res = 0;
        int length = s.length();
        if(length == 1)
            return 0;
        int cAll = 0;
        int cTemp = 0;
        int[] countAll = new int[26];
        int[] countTemp = new int[26];
        for (char c: s.toCharArray()){
            if(countAll[c-'a']==0)
                cAll++;
            countAll[c - 'a']++;
        }

        for (int i = 0; i < length - 1; i++) {
            if(--countAll[s.charAt(i) - 'a'] == 0){
                cAll--;
            }
            if(countTemp[s.charAt(i) - 'a']++ == 0){
                cTemp++;
            }
            if(cTemp == cAll)
                res++;
        }
        return res;
    }
}
