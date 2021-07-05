package com.example.leetcode.challenge.test2021.june.week4;


import java.util.HashSet;

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 *
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * Example 2:
 *
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 */
public class NumberMatchingSubsequences {
    public static void main(String[] args) {
        NumberMatchingSubsequences numberMatchingSubsequences = new NumberMatchingSubsequences();
        int res = numberMatchingSubsequences.numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"});
        System.out.println(res);
    }

    public int numMatchingSubseq(String s, String[] words) {
        int result = 0;
        HashSet<String> subSeqSet = new HashSet();
        HashSet<String> nonSeqSet = new HashSet();
        for(String word : words){
            if(subSeqSet.contains(word)){
                result++;
                continue;
            }else if(nonSeqSet.contains(word)){
                continue;
            }
            if(isSubseq(s,word)) {
                subSeqSet.add(word);
                result++;
            }else{
                nonSeqSet.add(word);
            }
        }
        return result;
    }

    public boolean isSubseq(String s, String word){
        int len1 = s.length();
        int len2 = word.length();
        int index = 0;
        for (int i = 0; i < len1 && index < len2; i++) {
            if(s.charAt(i) == word.charAt(index))
                index++;
            if(index == len2)
                return true;
        }
        return false;
    }
}
