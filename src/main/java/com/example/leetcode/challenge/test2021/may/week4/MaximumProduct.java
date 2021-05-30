package com.example.leetcode.challenge.test2021.may.week4;


/**
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 * Example 2:
 *
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 * Example 3:
 *
 * Input: words = ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 */
public class MaximumProduct {
    public static void main(String[] args) {
        MaximumProduct maximumProduct = new MaximumProduct();
        maximumProduct.maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"});
    }

    public int maxProduct(String[] words) {
        int length = words.length;
        int res = 0;
        int[] mask = new int[length];
        for (int i = 0; i < length; i++) {
            for(char c: words[i].toCharArray()){
                mask[i] |= 1 << (c - 'a');
            }

            for (int j = 0; j < i; ++j) {
                if ((mask[i] & mask[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }

        return res;
    }
}
