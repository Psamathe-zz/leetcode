package com.example.leetcode.challenge.test2021.march.week4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 *
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 *
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 *
 * Return a list of all universal words in A.  You can return the words in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * Output: ["apple","google","leetcode"]
 * Example 3:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 * Example 4:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 * Example 5:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 *
 */
public class WordSubsets {
    public static void main(String[] args) {
        WordSubsets wordSubsets = new WordSubsets();
        wordSubsets.wordSubsets(new String[]{"amazon","apple","facebook","google","leetcode"}, new String[]{"ec","oc","ceo"});
    }

    /**
     * https://www.cnblogs.com/grandyang/p/11623684.html
     * @param A
     * @param B
     * @return
     */
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> res = new ArrayList<>();
        int[] charCnt = new int[26];
        for (String b : B) {
            int[] t = helper(b);
            for (int i = 0; i < 26; ++i) {
                charCnt[i] = Math.max(charCnt[i], t[i]);
            }
        }
        for (String a : A) {
            int[] t = helper(a);
            int i = 0;
            for (; i < 26; ++i) {
                if (t[i] < charCnt[i])
                    break;
            }
            if (i == 26)
                res.add(a);
        }
        return res;
    }
    int[] helper(String word) {
        int[]  res = new int[26];
        for (char c : word.toCharArray())
            ++res[c - 'a'];
        return res;
    }
}
