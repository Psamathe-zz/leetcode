package com.example.leetcode.weeklycontest.old.test237;


/**
 * A pangram is a sentence where every letter of the English alphabet appears at least once.
 *
 * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
 * Output: true
 * Explanation: sentence contains at least one of every letter of the English alphabet.
 * Example 2:
 *
 * Input: sentence = "leetcode"
 * Output: false
 *
 */
public class CheckPangram {
    public static void main(String[] args) {
        CheckPangram checkPangram = new CheckPangram();
        checkPangram.checkIfPangram("jwtucoucmdfwxxqnxzkaxoglszmfrcvjoiunqqausaxxaaijyqdqgvdnqcaihwilqkpivenpnekioyqujrdrovqrlxovcucjqzjsxmllfgndfprctxvxwlzjtciqxgsxfwhmuzqvlksyuztoetyjugmswfjtawwaqmwyxmvo");
    }

    public boolean checkIfPangram(String sentence) {
        int length = sentence.length();
        if(length < 26)
            return false;
        int find = 0;
        int[] findIndex = new int[26];
        int val;
        for (int i = 0; i < length; i++) {
            val = findIndex[sentence.charAt(i) - 'a']++;
            if(val == 0) {
                find++;
            }
        }
        return find == 26;
    }
}
